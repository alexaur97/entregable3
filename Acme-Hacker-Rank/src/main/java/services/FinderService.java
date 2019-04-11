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
	private FinderRepository finderRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public FinderService(){
		super();
	}


	//Simple CRUD methods--------------------

	public void createFinder(Hacker hackerCreated) {
		Finder finder = new Finder();
		Collection<Position> positions = new ArrayList<Position>();
		finder.setKeyword("");
		finder.setHacker(hackerCreated);
		finder.setLastSearch(new Date());
		finder.setPositions(positions);
		this.finderRepository.save(finder);
	}

	public Collection<Finder> findAll(){
		Collection<Finder> result;

		result = finderRepository.findAll();

		return result;
	}

	public Finder findOne(int finderId){
		Finder result;

		result = finderRepository.findOne(finderId);

		return result;
	}

	public void save(Finder finder){
		Assert.notNull(finder);

		finderRepository.save(finder);
	}

	public void delete(Finder finder){
		finderRepository.delete(finder);
	}


	//Other Methods--------------------
} 
