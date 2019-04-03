package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.SocialProfileRepository;

import domain.SocialProfile; 

@Service 
@Transactional 
public class SocialProfileService { 

	//Managed repository -------------------
	@Autowired
	private SocialProfileRepository socialProfileRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public SocialProfileService(){
		super();
	}


	//Simple CRUD methods--------------------

	public SocialProfile create(){
		SocialProfile result;

		result = new SocialProfile();

		return result;
	}

	public Collection<SocialProfile> findAll(){
		Collection<SocialProfile> result;

		result = socialProfileRepository.findAll();

		return result;
	}

	public SocialProfile findOne(int socialProfileId){
		SocialProfile result;

		result = socialProfileRepository.findOne(socialProfileId);

		return result;
	}

	public void save(SocialProfile socialProfile){
		Assert.notNull(socialProfile);

		socialProfileRepository.save(socialProfile);
	}

	public void delete(SocialProfile socialProfile){
		socialProfileRepository.delete(socialProfile);
	}


	//Other Methods--------------------
} 
