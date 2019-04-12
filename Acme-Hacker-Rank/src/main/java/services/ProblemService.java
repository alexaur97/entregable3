
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ProblemRepository;
import domain.Application;
import domain.Company;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class ProblemService {

	//Managed repository -------------------
	@Autowired
	private ProblemRepository	problemRepository;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private PositionService		positionService;

	@Autowired
	private Validator			validator;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public ProblemService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Problem create() {
		this.companyService.findByPrincipal();
		Problem result;

		result = new Problem();

		return result;
	}

	public Collection<Problem> findAll() {
		Collection<Problem> result;

		result = this.problemRepository.findAll();

		return result;
	}

	public Problem findOne(final int problemId) {
		final Company principal = this.companyService.findByPrincipal();
		final Collection<Problem> problems = this.problemRepository.findAllByPrincipalId(principal.getId());
		final Problem result = this.problemRepository.findOne(problemId);
		Assert.isTrue(problems.contains(result));
		return result;
	}

	public Problem save(final Problem problem) {
		Assert.notNull(problem);
		this.companyService.findByPrincipal();
		if (problem.getId() != 0) {
			final Collection<Problem> problems = this.findAllByPrincipalId();
			final Problem retrieved = this.findOne(problem.getId());
			Assert.isTrue(problems.contains(retrieved));
			Assert.isTrue(retrieved.getMode().equals("DRAFT"));
		}
		final Problem saved = this.problemRepository.save(problem);
		return saved;
	}
	public void delete(final Problem problem) {
		Assert.notNull(problem);
		final Collection<Problem> problems = this.findAllByPrincipalId();
		Assert.isTrue(problems.contains(problem));
		final Collection<Application> applications = this.applicationService.findAllByProblem(problem.getId());
		if (!applications.isEmpty())
			this.applicationService.delete(applications);
		final Collection<Position> positions = this.positionService.findAllByProblem(problem.getId());
		if (!positions.isEmpty())
			for (final Position p : positions) {
				final Collection<Problem> ps = p.getProblems();
				ps.remove(problem);
				p.setProblems(ps);
				this.positionService.save(p);
			}
		this.problemRepository.delete(problem);
	}
	public Collection<Problem> findAllByPrincipalId() {
		final Company principal = this.companyService.findByPrincipal();
		final Integer companyId = principal.getId();
		final Collection<Problem> problems = this.problemRepository.findAllByPrincipalId(companyId);
		return problems;
	}

	public Problem reconstruct(final Problem problem, final BindingResult binding) {
		if (problem.getId() != 0) {
			final Problem retrieved = this.findOne(problem.getId());
			Assert.isTrue(retrieved.getMode().equals("DRAFT"));
		}
		final Company principal = this.companyService.findByPrincipal();
		final Problem result = problem;
		result.setCompany(principal);
		this.validator.validate(result, binding);
		return result;
	}

	//Other Methods--------------------
}
