package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.MiscellaniusDataRepository;

import domain.MiscellaniusData; 

@Service 
@Transactional 
public class MiscellaniusDataService { 

	//Managed repository -------------------
	@Autowired
	private MiscellaniusDataRepository miscellaniusDataRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public MiscellaniusDataService(){
		super();
	}


	//Simple CRUD methods--------------------

	public MiscellaniusData create(){
		MiscellaniusData result;

		result = new MiscellaniusData();

		return result;
	}

	public Collection<MiscellaniusData> findAll(){
		Collection<MiscellaniusData> result;

		result = miscellaniusDataRepository.findAll();

		return result;
	}

	public MiscellaniusData findOne(int miscellaniusDataId){
		MiscellaniusData result;

		result = miscellaniusDataRepository.findOne(miscellaniusDataId);

		return result;
	}

	public void save(MiscellaniusData miscellaniusData){
		Assert.notNull(miscellaniusData);

		miscellaniusDataRepository.save(miscellaniusData);
	}

	public void delete(MiscellaniusData miscellaniusData){
		miscellaniusDataRepository.delete(miscellaniusData);
	}


	//Other Methods--------------------
} 
