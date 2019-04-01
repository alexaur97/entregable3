package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.ProblemRepository;

import domain.Problem; 

@Service 
@Transactional 
public class ProblemService { 

	//Managed repository -------------------
	@Autowired
	private ProblemRepository problemRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public ProblemService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Problem create(){
		Problem result;

		result = new Problem();

		return result;
	}

	public Collection<Problem> findAll(){
		Collection<Problem> result;

		result = problemRepository.findAll();

		return result;
	}

	public Problem findOne(int problemId){
		Problem result;

		result = problemRepository.findOne(problemId);

		return result;
	}

	public void save(Problem problem){
		Assert.notNull(problem);

		problemRepository.save(problem);
	}

	public void delete(Problem problem){
		problemRepository.delete(problem);
	}


	//Other Methods--------------------
} 
