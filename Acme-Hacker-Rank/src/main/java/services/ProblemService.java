
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProblemRepository;
import domain.Company;
import domain.Problem;

@Service
@Transactional
public class ProblemService {

	//Managed repository -------------------
	@Autowired
	private ProblemRepository	problemRepository;

	@Autowired
	private CompanyService		companyService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public ProblemService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Problem create() {
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
		Problem result;

		result = this.problemRepository.findOne(problemId);

		return result;
	}

	public void save(final Problem problem) {
		Assert.notNull(problem);

		this.problemRepository.save(problem);
	}

	public void delete(final Problem problem) {
		this.problemRepository.delete(problem);
	}

	public Collection<Problem> findAllByPrincipalId() {
		final Company principal = this.companyService.findByPrincipal();
		final Integer companyId = principal.getId();
		final Collection<Problem> problems = this.problemRepository.findAllByPrincipalId(companyId);
		return problems;
	}

	//Other Methods--------------------
}
