
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import domain.Position;

@Service
@Transactional
public class PositionService {

	//Managed repository -------------------
	@Autowired
	private PositionRepository	positionRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public PositionService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Position create() {
		Position result;

		result = new Position();

		return result;
	}

	public Collection<Position> findAll() {
		Collection<Position> result;

		result = this.positionRepository.findAll();

		return result;
	}

	public Position findOne(final int positionId) {
		Position result;

		result = this.positionRepository.findOne(positionId);

		return result;
	}

	public void save(final Position position) {
		Assert.notNull(position);

		this.positionRepository.save(position);
	}

	public void delete(final Position position) {
		this.positionRepository.delete(position);
	}

	public Collection<Position> findByCompany(final Integer companyId) {
		Assert.notNull(companyId);
		final Collection<Position> res = this.positionRepository.findByCompany(companyId);
		return res;
	}
	//Other Methods--------------------
	public Collection<Position> searchPosition(final String keyword) {
		return this.positionRepository.searchPositionKeyWord(keyword);

	}

	public Collection<Double> statsPositionsPerCompany() {
		final Collection<Double> result = this.positionRepository.statsPositionsPerCompany();
		Assert.notNull(result);
		return result;
	}

	public Collection<Double> statsSalaryOfferedPerPosition() {
		final Collection<Double> result = this.positionRepository.statsSalaryOfferedPerPosition();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> bestPositionsSalary() {
		final Collection<Position> result = this.positionRepository.bestPositionsSalary();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> worstPositionsSalary() {
		final Collection<Position> result = this.positionRepository.worstPositionsSalary();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findByCompanyFinal(final Integer companyId) {
		Assert.notNull(companyId);
		final Collection<Position> res = this.positionRepository.findByCompanyFinal(companyId);
		return res;
	}

	public Collection<Position> findFinal() {
		final Collection<Position> result = this.positionRepository.findFinal();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findFinalNotBanned() {
		final Collection<Position> result = this.positionRepository.findFinalNotBanned();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findAllByProblem(final int id) {
		final Collection<Position> result = this.positionRepository.findAllByProblem(id);
		return result;
	}
}
