package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.ActorRepository;
import repositories.HackerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import domain.Company;
import domain.Hacker; 

@Service 
@Transactional 
public class HackerService { 

	//Managed repository -------------------
	@Autowired
	private HackerRepository hackerRepository;

	//Supporting Services ------------------
	@Autowired
	private ActorService actorService;


	//COnstructors -------------------------
	public HackerService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Hacker create(){
		Hacker result;

		result = new Hacker();

		return result;
	}

	public Collection<Hacker> findAll(){
		Collection<Hacker> result;

		result = hackerRepository.findAll();

		return result;
	}

	public Hacker findOne(int hackerId){
		Hacker result;

		result = hackerRepository.findOne(hackerId);

		return result;
	}

	public void save(Hacker hacker){
		Assert.notNull(hacker);

		hackerRepository.save(hacker);
	}

	public void delete(Hacker hacker){
		hackerRepository.delete(hacker);
	}


	//Other Methods--------------------
	
	private Hacker findByUserId(final int id) {
		final Hacker h = this.hackerRepository.findByUserId(id);
		return h;
	}

	public Hacker findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Hacker h = this.findByUserId(user.getId());
		Assert.notNull(h);
		this.actorService.auth(h, Authority.HACKER);
		return h;
	}} 


