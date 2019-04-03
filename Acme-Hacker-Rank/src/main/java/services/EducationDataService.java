package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.EducationDataRepository;

import domain.EducationData; 

@Service 
@Transactional 
public class EducationDataService { 

	//Managed repository -------------------
	@Autowired
	private EducationDataRepository educationDataRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public EducationDataService(){
		super();
	}


	//Simple CRUD methods--------------------

	public EducationData create(){
		EducationData result;

		result = new EducationData();

		return result;
	}

	public Collection<EducationData> findAll(){
		Collection<EducationData> result;

		result = educationDataRepository.findAll();

		return result;
	}

	public EducationData findOne(int educationDataId){
		EducationData result;

		result = educationDataRepository.findOne(educationDataId);

		return result;
	}

	public void save(EducationData educationData){
		Assert.notNull(educationData);

		educationDataRepository.save(educationData);
	}

	public void delete(EducationData educationData){
		educationDataRepository.delete(educationData);
	}


	//Other Methods--------------------
} 
