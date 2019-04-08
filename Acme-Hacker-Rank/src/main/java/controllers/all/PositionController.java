
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

}
