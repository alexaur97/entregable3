
package service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.MiscellaniusDataService;
import utilities.AbstractTest;
import domain.MiscellaniusData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MiscellaneousDataServiceTest extends AbstractTest {

	@Autowired
	private MiscellaniusDataService	miscellaneousDataService;


	//Requisito 17.1 Un hacker puede editar los Datos Miscelaneos de su curriculum
	@Test
	public void testCreateMiscellaneousDataGood() {
		super.authenticate("hacker1");

		final MiscellaniusData misData = this.miscellaneousDataService.create();

		final Collection<String> attachment = new ArrayList<>();
		attachment.add("http://attachments.com");
		attachment.add("http://otroEjemplo.com");
		misData.setAttachments(attachment);
		misData.setText("text");
		this.miscellaneousDataService.save(misData);
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que un Hacker modifique los datos
	// Miscelaneos, esto debe provocar un error porque los archivos adjuntos no tienen 
	//formato URL

	//An�lisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que los archivos adjuntos sean URLs.

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMiscellaneousDataError() throws ParseException {
		super.authenticate("hacker1");

		final MiscellaniusData misData = this.miscellaneousDataService.create();

		final Collection<String> attachment = new ArrayList<>();
		attachment.add("fallo");
		misData.setAttachments(attachment);
		misData.setText("text");
		this.miscellaneousDataService.save(misData);
		super.unauthenticate();
	}

	//Requisito 17.1 Un Actor autenticado como Hacker puede borrar los datos miscellaneous.
	@Test
	public void testDeleteMiscellaneousDataGood() {
		super.authenticate("hacker1");

		final int IdMiscellaneousData = super.getEntityId("miscellaniusData1");
		final MiscellaniusData misData = this.miscellaneousDataService.findOne(IdMiscellaneousData);

		this.miscellaneousDataService.delete(misData);
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que una Empresa elimine los datos miscellaneous
	// del curriculum de un Hacker, esto debe provocar un error porque solo esta autorizado
	//para esta accion un Hacker.
	//An�lisis del sentence coverage: el sistema al llamar al metodo del servicio "delete" comprueba
	// que el Actor es un hacker.

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteMiscellaneousDataError() {
		super.authenticate("company1");

		final int IdMiscellaneousData = super.getEntityId("miscellaniusData1");
		final MiscellaniusData misData = this.miscellaneousDataService.findOne(IdMiscellaneousData);

		this.miscellaneousDataService.delete(misData);
		super.unauthenticate();
	}

}
