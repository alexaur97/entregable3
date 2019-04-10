
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	//Managed repository -------------------
	@Autowired
	private FinderRepository	finderRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public FinderService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Finder create() {
		Finder result;

		result = new Finder();

		return result;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> result;

		result = this.finderRepository.findAll();

		return result;
	}

	public Finder findOne(final int finderId) {
		Finder result;

		result = this.finderRepository.findOne(finderId);

		return result;
	}

	public void save(final Finder finder) {
		Assert.notNull(finder);

		this.finderRepository.save(finder);
	}

	public void delete(final Finder finder) {
		this.finderRepository.delete(finder);
	}

	//Other Methods--------------------

	public Collection<Double> statsResultsFinders() {
		final Collection<Double> result = this.finderRepository.statsResultsFinders();
		Assert.notEmpty(result);
		return result;
	}

	public Collection<Double> emptyVsNonEmptyFindersRatio() {
		final Collection<Double> result = this.finderRepository.emptyVsNonEmptyFindersRatio();
		Assert.notEmpty(result);
		return result;
	}
}
