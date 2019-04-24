
package controllers.hacker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CurriculumService;
import services.HackerService;
import services.PositionService;
import controllers.AbstractController;
import domain.Application;
import domain.Curriculum;
import domain.Hacker;
import domain.Position;

@Controller
@RequestMapping("/application/hacker/")
public class ApplicationHackerController extends AbstractController {

	//Repository
	@Autowired
	private HackerService		hackerService;

	//Servicios

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private PositionService		positionService;

	@Autowired
	private CurriculumService	curriculumService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int applicationId) {
		ModelAndView result;
		try {
			final Hacker hacker = this.hackerService.findByPrincipal();
			final Application application = this.applicationService.findOne(applicationId);
			Assert.isTrue(application.getHacker().equals(hacker));
			result = new ModelAndView("application/show");
			result.addObject("application", application);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Hacker hacker = this.hackerService.findByPrincipal();
			final Collection<Application> applications;
			Collection<Application> applicationsPending;
			applicationsPending = this.applicationService.findApplicationsPendingByHacker(hacker.getId());
			applications = this.applicationService.findApplicationsHacker(hacker.getId());
			result = new ModelAndView("application/list");
			result.addObject("applications", applications);
			result.addObject("applicationsPending", applicationsPending);
			result.addObject("requestURI", "application/hacker/list.do");
			final String p = "PENDING";
			result.addObject("p", p);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		final Application application;
		application = new Application();

		try {

			final Collection<Position> positions = this.positionService.findPositionsFinal();
			final Collection<Curriculum> curriculums = this.curriculumService.findAllNotCopyByPrincipal();
			final Hacker hacker = this.hackerService.findByPrincipal();
			result = new ModelAndView("application/create");
			result.addObject("application", application);
			result.addObject("curriculums", curriculums);
			result.addObject("positions", positions);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Application application, final BindingResult binding) {
		ModelAndView res;
		final Application applicationFinal = this.applicationService.recostructionCreate(application, binding);
		final Collection<Application> applications;
		Collection<Application> applicationsPending;
		if (binding.hasErrors()) {
			final Collection<Position> positions = this.positionService.findPositionsFinal();
			final Collection<Curriculum> curriculums = this.curriculumService.findAllByPrincipal();
			res = new ModelAndView("application/create");
			res.addObject("curriculums", curriculums);
			res.addObject("positions", positions);
		} else
			try {
				final Hacker hacker = this.hackerService.findByPrincipal();
				final String p = "PENDING";
				this.applicationService.saveHacker(applicationFinal);
				res = new ModelAndView("redirect:/application/hacker/list.do");

			} catch (final Throwable oops) {

				res = new ModelAndView("redirect:/#");
			}
		return res;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {

		ModelAndView result;
		final Application application;

		try {
			application = this.applicationService.findOne(applicationId);
			Assert.isTrue(application.getStatus().equals("PENDING"));
			final Hacker hacker = this.hackerService.findByPrincipal();
			final Collection<Position> positions = this.positionService.findPositionsFinal();
			Assert.isTrue(application.getHacker().equals(hacker));
			result = new ModelAndView("application/edit");
			result.addObject("application", application);
			result.addObject("positions", positions);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(final Application application, final BindingResult binding) {
		ModelAndView res;
		final Application applicationFinal = this.applicationService.recostructionEdit(application, binding);
		final Collection<Application> applications;
		if (binding.hasErrors()) {
			final Collection<Position> positions = this.positionService.findPositionsFinal();
			final Collection<Curriculum> curriculums = this.curriculumService.findAllByPrincipal();
			res = new ModelAndView("application/create");
			res.addObject("curriculums", curriculums);
			res.addObject("positions", positions);
		} else
			try {
				final Hacker hacker = this.hackerService.findByPrincipal();
				final String p = "PENDING";
				this.applicationService.saveHacker(applicationFinal);
				res = new ModelAndView("redirect:/application/hacker/list.do");

			} catch (final Throwable oops) {
				if (application.getExplanation().isEmpty() || application.getCodeLink().isEmpty()) {
					res = new ModelAndView("application/edit");
					res.addObject("application", application);
					res.addObject("message", "application.explanationLink.error");

				} else
					res = new ModelAndView("redirect:/#");
			}
		return res;
	}
}
