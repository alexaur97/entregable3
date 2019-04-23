
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.ApplicationService;
import services.CurriculumService;
import services.PositionService;
import utilities.AbstractTest;
import domain.Application;
import domain.Curriculum;
import domain.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private PositionService		positionService;
	@Autowired
	private CurriculumService	curriculumService;


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
	@Test
	public void testCreateApplicationGood() {
		super.authenticate("hacker1");
		final Application application = new Application();
		final int positionId = super.getEntityId("position1");
		final Position position = this.positionService.findOne(positionId);
		application.setPosition(position);
		final int curriculumId = super.getEntityId("curriculum1");
		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);
		application.setCurriculum(curriculum);
		final Application applicationFinal = this.applicationService.recostructionCreate(application, null);
		this.applicationService.saveHacker(applicationFinal);
	}
	@Test(expected = NullPointerException.class)
	public void testCreateApplicationBad() {
		super.authenticate("hacker1");
		final Application application = new Application();
		final Application applicationFinal = this.applicationService.recostructionCreate(application, null);
		this.applicationService.saveHacker(applicationFinal);
	}
	@Test
	public void testEditApplicationGood() {
		super.authenticate("hacker1");
		final int applicationId = super.getEntityId("");
		final Application application = this.applicationService.findOne(applicationId);
		application.setExplanation("HOLA");
		application.setCodeLink("HOLA");
		final Application applicationFinal = this.applicationService.recostructionEdit(application, null);
		this.applicationService.saveHacker(applicationFinal);

	}
	@Test(expected = NullPointerException.class)
	public void testEditApplicationBad() {
		super.authenticate("hacker1");
		final int applicationId = super.getEntityId("");
		final Application application = this.applicationService.findOne(applicationId);
		application.setExplanation("");
		application.setCodeLink("");
		final Application applicationFinal = this.applicationService.recostructionEdit(application, null);
		this.applicationService.saveHacker(applicationFinal);

	}
}
