
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

import services.EducationDataService;
import utilities.AbstractTest;
import domain.EducationData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class EducationDataServiceTest extends AbstractTest {

	@Autowired
	private EducationDataService	educationDataService;


	//Requisito 17.1 Un hacker puede editar los Datos Academicos de su curriculum
	@Test
	public void testEditEducationDataGood() {
		super.authenticate("hacker1");

		final int IdEducationData = super.getEntityId("educationData1");
		final EducationData edData = this.educationDataService.findOne(IdEducationData);

		edData.setMark("10");
		this.educationDataService.save(edData);
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que una Empresa modifique una posicion
	// cambiando su modo a FINAL sin tener al menos dos problemas asociados
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "saveMode" comprueba
	// que el la posicion tenga dos o mas problemas asociados.

	@Test(expected = IllegalArgumentException.class)
	public void testEditEducationDataError() throws ParseException {
		super.authenticate("hacker1");

		final int IdEducationData = super.getEntityId("educationData1");
		final EducationData edData = this.educationDataService.findOne(IdEducationData);

		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		final String stringFecha = "02/09/2026";
		final Date fecha = sdf.parse(stringFecha);

		edData.setEndDate(fecha);
		this.educationDataService.save(edData);
		super.unauthenticate();
	}

	//Requisito 9.1 Un Actor autenticado como Empresa puede borrar una posicion.

	@Test
	public void testDeleteEducationDataGood() {
		super.authenticate("hacker1");

		final int IdEducationData = super.getEntityId("educationData1");
		final EducationData edData = this.educationDataService.findOne(IdEducationData);

		this.educationDataService.delete(edData);
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que una Empresa elimine una posicion
	// en modo FINAL , esto debe provocar un error porque en estado final solo se podria cancelar,
	//pero no eliminar la posicion.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "delete" comprueba
	// que el la posicion esta en modo borrador.

	@Test(expected = IllegalArgumentException.class)
	public void testDeletePositionError() {
		super.authenticate("company1");

		final int IdEducationData = super.getEntityId("educationData1");
		final EducationData edData = this.educationDataService.findOne(IdEducationData);

		this.educationDataService.delete(edData);
		super.unauthenticate();
	}

}
