
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Repositorios propios
	@Autowired
	private AdministratorRepository			administratorRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Servicios ajenos

	// Métodos CRUD

	public Collection<Administrator> findAll() {
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.ADMIN));
		Collection<Administrator> result;
		result = this.administratorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrator findOne(final int administratorId) {
		Assert.isTrue(administratorId != 0);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.ADMIN));
		Administrator result;
		result = this.administratorRepository.findOne(administratorId);
		return result;
	}
	public void delete(final Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.ADMIN));
		this.administratorRepository.delete(administrator);
	}

	public void save(final Administrator administrator) {
		if (administrator.getId() != 0) {
			final Authority auth = new Authority();
			auth.setAuthority(Authority.ADMIN);
			Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(auth));
		}
		Assert.notNull(administrator);
		this.administratorRepository.save(administrator);
	}

	// FR 12.1
	public Administrator create() {
		this.findByPrincipal();
		final Administrator result = new Administrator();
		final UserAccount ua = new UserAccount();
		result.setUserAccount(ua);
		final Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		result.getUserAccount().setAuthorities(authorities);

		return result;

	}

	public Administrator findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Administrator a = this.findByUserId(user.getId());
		Assert.notNull(a);
		this.actorService.auth(a, Authority.ADMIN);
		return a;
	}

	public Administrator findByUserId(final int id) {
		final Administrator a = this.administratorRepository.findByUserId(id);
		return a;
	}

}
