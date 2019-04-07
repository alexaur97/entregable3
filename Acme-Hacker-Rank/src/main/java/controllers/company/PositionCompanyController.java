
package controllers.company;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import services.PositionService;
import controllers.AbstractController;
import domain.Company;
import domain.Position;

@Controller
@RequestMapping("/position/company/")
public class PositionCompanyController extends AbstractController {

	//Repository
	@Autowired
	private CompanyService	companyService;

	//Servicios

	@Autowired
	private PositionService	positionService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Company company = this.companyService.findByPrincipal();
			Collection<Position> positions;

			positions = this.positionService.findByCompany(company.getId());
			result = new ModelAndView("position/list");
			result.addObject("requestURI", "position/list.do");
			result.addObject("positions", positions);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	//	@RequestMapping(value = "/create", method = RequestMethod.GET)
	//	public ModelAndView create() {
	//
	//		ModelAndView result;
	//		Float floaat;
	//		floaat = new Float();
	//
	//		try {
	//			final Brotherhood bh = this.brotherhoodService.findByPrincipal();
	//			floaat.setId(0);
	//			floaat.setBrotherhood(bh);
	//
	//			result = new ModelAndView("float/list");
	//			result.addObject("floaat", floaat);
	//			result.addObject("brotherhood", this.brotherhoodService.findByPrincipal());
	//			result = this.createEditModelAndView(floaat);
	//		} catch (final Throwable oops) {
	//			result = new ModelAndView("redirect:/#");
	//
	//		}
	//
	//		return result;
	//	}
	//
	//	// Edition ----------------------------------------------------------------
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int floatId) {
	//		ModelAndView res = new ModelAndView("float/edit");
	//		try {
	//			final Float floaat = this.floatService.findOne(floatId);
	//			Assert.notNull(floaat);
	//			final Integer idB = this.brotherhoodService.findByPrincipal().getId();
	//			final Collection<Float> floats = this.floatService.findFloatsByBrotherhood(idB);
	//			Assert.isTrue(floats.contains(floaat));
	//			res.addObject("floaat", floaat);
	//			res.addObject("brotherhood", this.brotherhoodService.findByPrincipal());
	//
	//		} catch (final Throwable oops) {
	//			res = new ModelAndView("redirect:/#");
	//		}
	//		return res;
	//	}
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@ModelAttribute("floaat") Float floaat, final BindingResult binding) {
	//		ModelAndView res;
	//
	//		floaat = this.floatService.reconstruct(floaat, binding);
	//
	//		if (binding.hasErrors())
	//			res = this.createEditModelAndView(floaat);
	//		else
	//			try {
	//				final Boolean b = this.floatService.validatePictures(floaat.getPictures());
	//				if (!b) {
	//					res = this.createEditModelAndView(floaat, "float.picture.error");
	//					res.addObject("message", "float.picture.error");
	//					res.addObject("brotherhood", this.brotherhoodService.findByPrincipal());
	//
	//				} else {
	//					this.floatService.save(floaat);
	//					res = new ModelAndView("redirect:/brotherhood/float/list.do");
	//					res.addObject("brotherhood", this.brotherhoodService.findByPrincipal());
	//
	//				}
	//			} catch (final Throwable oops) {
	//				final Boolean b = this.floatService.validatePictures(floaat.getPictures());
	//				if (!b)
	//					res = this.createEditModelAndView(floaat, "float.picture.error");
	//				res = this.createEditModelAndView(floaat, "float.commit.error");
	//				res.addObject("brotherhood", this.brotherhoodService.findByPrincipal());
	//
	//			}
	//
	//		return res;
	//	}
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

}
