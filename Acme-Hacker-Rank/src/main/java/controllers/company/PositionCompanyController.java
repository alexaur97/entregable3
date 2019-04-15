
package controllers.company;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import services.PositionService;
import services.ProblemService;
import controllers.AbstractController;
import domain.Company;
import domain.Position;
import domain.Problem;

@Controller
@RequestMapping("/position/company/")
public class PositionCompanyController extends AbstractController {

	//Repository
	@Autowired
	private CompanyService	companyService;

	//Servicios
	@Autowired
	private ProblemService	problemService;

	@Autowired
	private PositionService	positionService;


	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Company company = this.companyService.findByPrincipal();
			Collection<Position> positions;

			positions = this.positionService.findByCompany(company.getId());
			result = new ModelAndView("position/myList");
			result.addObject("requestURI", "position/myList.do");
			result.addObject("positions", positions);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Position position;
		position = new Position();

		try {
			final Company c = this.companyService.findByPrincipal();
			position.setId(0);
			position.setCompany(c);

			result = this.createEditModelAndView(position);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int positionId) {
		ModelAndView res;
		try {

			final Position position = this.positionService.findOne(positionId);
			Assert.notNull(position);
			Assert.isTrue(position.getMode().equals("DRAFT"));
			final Integer idC = this.companyService.findByPrincipal().getId();
			final Collection<Position> positions = this.positionService.findByCompany(idC);
			Assert.isTrue(positions.contains(position));
			res = this.createEditModelAndView(position);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("position") Position position, final BindingResult binding) {
		ModelAndView res;

		position = this.positionService.reconstruct(position, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(position);
		else
			try {

				this.positionService.save(position);
				res = new ModelAndView("redirect:/position/company/myList.do");

			} catch (final Throwable oops) {

				res = this.createEditModelAndView(position, "position.commit.error");

			}

		return res;
	}
	//	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final Float floaat, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		try {
	//			this.floatService.delete(floaat);
	//			result = new ModelAndView("redirect:/brotherhood/float/list.do");
	//
	//		} catch (final Throwable oops) {
	//			result = new ModelAndView("float/edit");
	//			result.addObject("floaat", floaat);
	//			result.addObject("message", oops.getMessage());
	//			final String msg = oops.getMessage();
	//			if (msg.equals("floatcannotDelete")) {
	//				final Boolean floatcannotDelete = true;
	//				result.addObject("floatcannotDelete", floatcannotDelete);
	//				result.addObject("brotherhood", this.brotherhoodService.findByPrincipal());
	//
	//			}
	//		}
	//
	//		return result;
	//	}

	protected ModelAndView createEditModelAndView(final Position position) {
		return this.createEditModelAndView(position, null);
	}
	protected ModelAndView createEditModelAndView(final Position position, final String messageCode) {
		final ModelAndView res;
		res = new ModelAndView("position/edit");
		final Collection<Problem> problems = this.problemService.findAllByPrincipalId();
		res.addObject("problems", problems);
		res.addObject("position", position);
		res.addObject("message", messageCode);

		return res;
	}

}
