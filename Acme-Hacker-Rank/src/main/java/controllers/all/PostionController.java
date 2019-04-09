
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PositionService;
import controllers.AbstractController;
import domain.Position;

@Controller
@RequestMapping("/position/")
public class PostionController extends AbstractController {

	@Autowired
	private PositionService	positionService;


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
}
