package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.CompanyRepository;

import domain.Company; 

@Service 
@Transactional 
public class CompanyService { 

	//Managed repository -------------------
	@Autowired
	private CompanyRepository companyRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public CompanyService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Company create(){
		Company result;

		result = new Company();

		return result;
	}

	public Collection<Company> findAll(){
		Collection<Company> result;

		result = companyRepository.findAll();

		return result;
	}

	public Company findOne(int companyId){
		Company result;

		result = companyRepository.findOne(companyId);

		return result;
	}

	public void save(Company company){
		Assert.notNull(company);

		companyRepository.save(company);
	}

	public void delete(Company company){
		companyRepository.delete(company);
	}


	//Other Methods--------------------
} 
