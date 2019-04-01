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
	private PositionRepository positionRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public PositionService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Position create(){
		Position result;

		result = new Position();

		return result;
	}

	public Collection<Position> findAll(){
		Collection<Position> result;

		result = positionRepository.findAll();

		return result;
	}

	public Position findOne(int positionId){
		Position result;

		result = positionRepository.findOne(positionId);

		return result;
	}

	public void save(Position position){
		Assert.notNull(position);

		positionRepository.save(position);
	}

	public void delete(Position position){
		positionRepository.delete(position);
	}


	//Other Methods--------------------
} 
