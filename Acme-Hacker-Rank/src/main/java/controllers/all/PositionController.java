
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PositionService;
import domain.Position;

@Controller
@RequestMapping("/position")
public class PositionController {

	@Autowired
	private PositionService	positionService;


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchForm() {
		ModelAndView result;
		final Position position = new Position();
		try {
			result = new ModelAndView("position/search");
			result.addObject("position", position);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchList(final Position position, final BindingResult binding) {
		ModelAndView result;
		try {
			result = new ModelAndView("position/search");
			final Collection<Position> positions = this.positionService.searchPosition(position.getTitle());
			result.addObject("positions", positions);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<Position> positions;
			positions = this.positionService.findAll();
			result = new ModelAndView("position/list");
			result.addObject("requestURI", "position/list.do");
			result.addObject("positions", positions);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/listByCompany", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int companyId) {
		ModelAndView result;
		try {
			Collection<Position> positions;
			positions = this.positionService.findByCompany(companyId);
			result = new ModelAndView("position/list");
			result.addObject("requestURI", "position/list.do");
			result.addObject("positions", positions);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int positionId) {
		ModelAndView result;
		final Position position;

		try {
			Assert.notNull(positionId);
			position = this.positionService.findOne(positionId);
			result = new ModelAndView("position/show");
			result.addObject("position", position);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

}