package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.CurriculumRepository;

import domain.Curriculum; 

@Service 
@Transactional 
public class CurriculumService { 

	//Managed repository -------------------
	@Autowired
	private CurriculumRepository curriculumRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public CurriculumService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Curriculum create(){
		Curriculum result;

		result = new Curriculum();

		return result;
	}

	public Collection<Curriculum> findAll(){
		Collection<Curriculum> result;

		result = curriculumRepository.findAll();

		return result;
	}

	public Curriculum findOne(int curriculumId){
		Curriculum result;

		result = curriculumRepository.findOne(curriculumId);

		return result;
	}

	public void save(Curriculum curriculum){
		Assert.notNull(curriculum);

		curriculumRepository.save(curriculum);
	}

	public void delete(Curriculum curriculum){
		curriculumRepository.delete(curriculum);
	}


	//Other Methods--------------------
} 
