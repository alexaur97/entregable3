
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Hacker;
import domain.Position;

@Service
@Transactional
public class FinderService {

	//Managed repository -------------------
	@Autowired
	private FinderRepository	finderRepository;


	//Supporting Services ------------------
	@Autowired
	private ConfigurationParametersService	configParamsService;
	
	@Autowired
	private PositionService	positionService;
	
	@Autowired
	private HackerService	hackerService;
	
	//COnstructors -------------------------
	public FinderService() {
		super();
	}

	//Simple CRUD methods--------------------

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
	
	public Finder getFinderFromHacker(int id) {
		return this.finderRepository.getFinderFromHacker(id);
	}
	
	public void createFinder(Hacker hackerCreated) {
		Finder finder = new Finder();
		Collection<Position> positions = new ArrayList<Position>();
		finder.setKeyword("");
		finder.setHacker(hackerCreated);
		finder.setLastSearch(new Date());
		finder.setPositions(positions);
		this.finderRepository.save(finder);
	}

	public void save(Finder finder) {
		Assert.notNull(finder);
		finder.setLastSearch(new Date());
		finder.setHacker(this.hackerService.findByPrincipal());
		finder.setPositions(this.positionService.searchPositions(finder.getKeyword(), finder.getMinSalary(), finder.getMaxSalary(), finder.getDeadline()));
		this.finderRepository.save(finder);
	}

	public Finder reconstruct(Finder finder) {
		Assert.notNull(finder);
		Finder result = this.finderRepository.findOne(finder.getId());
		result.setDeadline(finder.getDeadline());
		result.setKeyword(finder.getKeyword());
		result.setMaxSalary(finder.getMaxSalary());
		result.setMinSalary(finder.getMinSalary());
		return result;
	}

	public void saveAfterClean(Finder finder) {
		Assert.notNull(finder);
		Collection<Position> positions = new ArrayList<Position>();
		finder.setLastSearch(new Date());
		finder.setHacker(this.hackerService.findByPrincipal());
		finder.setPositions(positions);
		this.finderRepository.save(finder);
	}
	
	public void cleanFinder(Finder finder){
		Assert.notNull(finder);
		Collection<Position> positions = new ArrayList<Position>();
		finder.setDeadline(null);
		finder.setKeyword("");
		finder.setMaxSalary(null);
		finder.setMinSalary(null);
		finder.setLastSearch(new Date());

		finder.setPositions(positions);
		this.finderRepository.save(finder);		
	}
	
	public void cleanCacheIfNecessary() {
		int cachedHours = this.configParamsService.find().getFinderCachedHours();
		Collection<Finder> finders = finderRepository.findAll();
		Date now = new Date();
		for(Finder f: finders){
			if((now.getTime()-f.getLastSearch().getTime())/3600000 >= cachedHours){
				cleanFinder(f);
			}
		}
		
	}
}
