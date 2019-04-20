
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


	@Test
	public void testCopyCurriculum() {
		super.authenticate("hacker1");
		final Hacker principal = this.hackerService.findByPrincipal();
		final Curriculum curriculum = (Curriculum) principal.getCurriculums().toArray()[0];
		final Curriculum copy = this.curriculumService.copyCurriculum(curriculum);
		Assert.isTrue(curriculum.getId() != copy.getId());
		super.unauthenticate();
	}

}
