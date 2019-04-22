
package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CompanyService;
import services.PositionService;
import utilities.AbstractTest;
import domain.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PositionServiceTest extends AbstractTest {

	@Autowired
	private PositionService	positionService;

	@Autowired
	private CompanyService	companyService;


	//Requisito 9.1 Un Actor autenticado como Empresa puede crear una posicion.
	@Test
	public void testCreatePositionGood() throws ParseException {
		super.authenticate("company1");
		Position position = this.positionService.create();
		position.setTitle("title");
		position.setDescription("description");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "02-09-2020";
		final Date fecha = sdf.parse(stringFecha);
		position.setDeadline(fecha);
		position.setProfileRequired("profileRequired");
		position.setSkillRequired("skillRequired");
		position.setTechRequired("techRequired");
		position.setSalaryOffered(200);
		position = this.positionService.reconstruct(position, null);
		this.positionService.save(position);
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePositionError() throws ParseException {
		super.authenticate("hacker1");
		Position position = this.positionService.create();
		position.setTitle("title");
		position.setDescription("description");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String stringFecha = "02-09-2020";
		final Date fecha = sdf.parse(stringFecha);
		position.setDeadline(fecha);
		position.setProfileRequired("profileRequired");
		position.setSkillRequired("skillRequired");
		position.setTechRequired("techRequired");
		position.setSalaryOffered(200);
		position = this.positionService.reconstruct(position, null);
		this.positionService.save(position);
		super.unauthenticate();
	}

}
