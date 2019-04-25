
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.CurriculumService;
import services.HackerService;
import utilities.AbstractTest;
import domain.Curriculum;
import domain.Hacker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CurriculumServiceTest extends AbstractTest {

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private HackerService		hackerService;


	//Requisito 17.1 Un hacker puede editar un currículum que le pertenezca

	@Test
	public void testEditCurriculum() {
		super.authenticate("hacker1");
		final Hacker principal = this.hackerService.findByPrincipal();
		final Curriculum curriculum = (Curriculum) principal.getCurriculums().toArray()[0];
		curriculum.setIdName("Setting");
		this.curriculumService.save(curriculum);
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que un Hacker modifique un currículum ajeno
	//esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "save" comprueba
	// que el currículum a editar no pertenece a la lista de currículums del hacker logueado.

	@Test(expected = IllegalArgumentException.class)
	public void testEditCurriculumOtherHacker() {
		super.authenticate("hacker2");
		this.hackerService.findByPrincipal();
		final int idH = super.getEntityId("actor5");
		final Curriculum curriculum = (Curriculum) this.curriculumService.findByHacker(idH).toArray()[0];
		curriculum.setIdName("Setting");
		this.curriculumService.save(curriculum);
		super.unauthenticate();
	}

	//Requisito 17.1 Un hacker puede eliminar un currículum que le pertenezca

	@Test
	public void testDeleteCurriculum() {
		super.authenticate("hacker1");
		final Hacker principal = this.hackerService.findByPrincipal();
		final Curriculum curriculum = (Curriculum) principal.getCurriculums().toArray()[0];

		this.curriculumService.delete(curriculum);
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que un Hacker elimine un currículum ajeno
	//esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "delete" comprueba
	// que el currículum a eliminar no pertenece a la lista de currículums del hacker logueado.

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteCurriculumOtherHacker() {
		super.authenticate("hacker2");
		this.hackerService.findByPrincipal();
		final int idH = super.getEntityId("actor5");
		final Curriculum curriculum = (Curriculum) this.curriculumService.findByHacker(idH).toArray()[0];
		curriculum.setIdName("Setting");
		this.curriculumService.delete(curriculum);
		super.unauthenticate();
	}

	//Requisito 17.1 Un hacker puede crear la copia de un currículum que le pertenezca

	@Test
	public void testCopyCurriculum() {
		super.authenticate("hacker1");
		final Hacker principal = this.hackerService.findByPrincipal();
		final Curriculum curriculum = (Curriculum) principal.getCurriculums().toArray()[0];
		final Curriculum copy = this.curriculumService.copyCurriculum(curriculum);
		Assert.isTrue(curriculum.getId() != copy.getId());
		super.unauthenticate();
	}

	//	Para el caso negativo estamos intentando que un Hacker cree la copiia de un currículum ajeno
	//esto debe provocar un error.
	//Análisis del sentence coverage: el sistema al llamar al metodo del servicio "copyCurriculum" comprueba
	// que el currículum a copiar no pertenece a la lista de currículums del hacker logueado.

	@Test(expected = IllegalArgumentException.class)
	public void testCopyCurriculumOtherHacker() {
		super.authenticate("hacker2");
		this.hackerService.findByPrincipal();
		final int idH = super.getEntityId("actor5");
		final Curriculum curriculum = (Curriculum) this.curriculumService.findByHacker(idH).toArray()[0];
		final Curriculum copy = this.curriculumService.copyCurriculum(curriculum);
		Assert.isTrue(curriculum.getId() != copy.getId());
		super.unauthenticate();
	}

}
