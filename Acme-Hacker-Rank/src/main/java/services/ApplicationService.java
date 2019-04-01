package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.ApplicationRepository;

import domain.Application; 

@Service 
@Transactional 
public class ApplicationService { 

	//Managed repository -------------------
	@Autowired
	private ApplicationRepository applicationRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public ApplicationService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Application create(){
		Application result;

		result = new Application();

		return result;
	}

	public Collection<Application> findAll(){
		Collection<Application> result;

		result = applicationRepository.findAll();

		return result;
	}

	public Application findOne(int applicationId){
		Application result;

		result = applicationRepository.findOne(applicationId);

		return result;
	}

	public void save(Application application){
		Assert.notNull(application);

		applicationRepository.save(application);
	}

	public void delete(Application application){
		applicationRepository.delete(application);
	}


	//Other Methods--------------------
} 
