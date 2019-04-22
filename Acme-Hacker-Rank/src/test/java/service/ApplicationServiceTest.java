
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.ApplicationService;
import utilities.AbstractTest;
import domain.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	@Autowired
	private ApplicationService	applicationService;


	//REQUISITO 9.3 SOLO COGER UN COMPANY CON UNA APPLICATION SUBMITTED  Y ACEPTARLA
	@Test
	public void testAcceptApplicationGood() {
		super.authenticate("company1");
		final int applicationId = super.getEntityId("application3");
		final Application application = this.applicationService.acceptApplicationChanges(applicationId);
		this.applicationService.saveCompany(application);
	}
	//REQUISITO 9.3 SOLO COGER UN COMPANY CON UNA APPLICATION RECHAZADA E INTENTAR ACEPTARLA

	@Test(expected = IllegalArgumentException.class)
	public void testAcceptApplicationError() {
		super.authenticate("company1");
		final int applicationId = super.getEntityId("application2");
		final Application application = this.applicationService.acceptApplicationChanges(applicationId);
		this.applicationService.saveCompany(application);
	}
	//REQUISITO 9.3 SOLO COGER UN COMPANY CON UNA APPLICATION SUBMITTED  Y RECHAZARLA

	@Test
	public void testRejectApplicationGood() {
		super.authenticate("company1");
		final int applicationId = super.getEntityId("application3");
		final Application application = this.applicationService.rejectApplicationChanges(applicationId);
		this.applicationService.saveCompany(application);
	}
	//REQUISITO 9.3 SOLO COGER UN COMPANY CON UNA APPLICATION ACEPTADA E INTENTAR RECHAZARLA

	@Test(expected = IllegalArgumentException.class)
	public void testRejectApplicationError() {
		super.authenticate("company1");
		final int applicationId = super.getEntityId("application1");
		final Application application = this.applicationService.acceptApplicationChanges(applicationId);
		this.applicationService.saveCompany(application);
	}
}
