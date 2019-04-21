
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import controllers.AbstractController;

@Controller
@RequestMapping("/spam/administrator")
public class SpamAdministratorController extends AbstractController {

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/spammer", method = RequestMethod.GET)
	public ModelAndView spammer() {
		ModelAndView result;

		try {

			this.actorService.isSpammer();
			result = new ModelAndView("spammer/spammer");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

}
