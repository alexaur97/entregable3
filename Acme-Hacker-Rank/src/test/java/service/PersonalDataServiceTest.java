
package service;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.CurriculumService;
import services.PersonalDataService;
import utilities.AbstractTest;
import domain.PersonalData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PersonalDataServiceTest extends AbstractTest {

	@Autowired
	private PersonalDataService	personalDataService;

	@Autowired
	private CurriculumService	curriculumService;


	//Requisito 17.1 Un hacker puede editar los Datos personales de su curriculum

	@Test
	public void testEditEducationData() {
		super.authenticate("hacker1");

		final int IdPersonalData = super.getEntityId("personalData1");
		final PersonalData personalData = this.personalDataService.findOne(IdPersonalData);
		personalData.setFullname("Setting");
		this.personalDataService.save(personalData);
		super.unauthenticate();
	}

	// Para el caso negativo estamos intentando que un Hacker modifique los datos
	// personales de un currículum ajeno
	// esto debe provocar un error.
	// Análisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que los datos personales no pertenecen a ningún currículum de la lista de currículums del hacker
	// logueado

	@Test(expected = IllegalArgumentException.class)
	public void testEditEducationDataOtherHacker() throws ParseException {
		super.authenticate("hacker2");

		final int IdPersonalData = super.getEntityId("personalData1");
		final PersonalData personalData = this.personalDataService.findOne(IdPersonalData);
		personalData.setFullname("Setting");
		this.personalDataService.save(personalData);
		super.unauthenticate();
	}

}
