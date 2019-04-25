
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import security.UserAccount;
import services.HackerService;
import utilities.AbstractTest;
import domain.CreditCard;
import domain.Hacker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class HackerServiceTest extends AbstractTest {

	@Autowired
	private HackerService	hackerService;


	//Este test testea el requisito 7.1  Un actor no autenticado 
	//puede registrarse en el sistema como HACKER

	// An�lisis del sentence coverage (Pasos que sigue el test en nuestro c�digo): 
	// 1. El usuario rellena los datos obligatorios para crear su cuenta.
	// 2. El hacker se crea correctamente

	// An�lisis del data coverage (�Que y como estamos verificando en nuestro modelo de datos?):

	// Estamos verificando en nuestro modelo de datos que un usuario puede registrarse como Hacker en el sistema

	@Test
	public void testCreateHacker() {
		final Hacker h = this.hackerService.create();
		h.setBanned(false);
		final CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("bn");
		creditCard.setCvv(111);
		creditCard.setExpirationMonth(11);
		creditCard.setExpirationYear(88);
		creditCard.setNumber("4296908289844192");
		creditCard.setHolderName("Mike");
		h.setCreditCard(creditCard);
		h.setEmail("email@email.com");
		h.setName("name");
		h.setPhone("9999999");
		h.setPhoto("https://cdn.pixabay.com/photo/2014/06/01/21/05/photo-effect-359981_960_720.jpg");
		h.setSpammer(false);
		h.setSurnames("alfa beta");
		h.setVAT("ESA9999999B");

		final UserAccount userAccount = h.getUserAccount();
		userAccount.setPassword("password");
		userAccount.setUsername("username");
		h.setUserAccount(userAccount);

		this.hackerService.save(h);
	}

}
