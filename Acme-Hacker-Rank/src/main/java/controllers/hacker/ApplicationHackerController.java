
package controllers.hacker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.HackerService;
import services.PositionService;
import controllers.AbstractController;
import domain.Application;
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


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Hacker hacker = this.hackerService.findByPrincipal();
			final Collection<Application> applications;
			applications = this.applicationService.findApplicationsByHacker(hacker.getId());
			result = new ModelAndView("application/list");
			result.addObject("requestURI", "application/list.do");
			result.addObject("applications", applications);
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
			final Hacker hacker = this.hackerService.findByPrincipal();
			result = new ModelAndView("application/create");
			result.addObject("application", application);
			result.addObject("positions", positions);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/#");

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {

		ModelAndView result;
		final Application application;

		try {
			application = this.applicationService.findOne(applicationId);
			final Collection<Position> positions = this.positionService.findPositionsFinal();
			final Hacker hacker = this.hackerService.findByPrincipal();
			Assert.isTrue(application.getHacker() == hacker);
			result = new ModelAndView("application/edit");
			result.addObject("application", application);
			result.addObject("positions", positions);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
}
