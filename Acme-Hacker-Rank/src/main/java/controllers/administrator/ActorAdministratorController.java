
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("actor/administrator")
public class ActorAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ActorService			actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			this.administratorService.findByPrincipal();
			final Collection<Actor> actors = this.actorService.findAll();
			result = new ModelAndView("actor/list");
			result.addObject("requestURI", "actor/administrator/list.do");
			result.addObject("actors", actors);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int actorId) {
		ModelAndView result;
		final Collection<Actor> actors;
		try {
			this.administratorService.findByPrincipal();
			final Actor actor = this.actorService.findOne(actorId);
			actor.setBanned(true);
			this.actorService.save(actor);
			actors = this.actorService.findAll();
			result = new ModelAndView("actor/list");
			result.addObject("requestURI", "actor/administrator/ban.do");
			result.addObject("actors", actors);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam final int actorId) {
		ModelAndView result;
		final Collection<Actor> actors;
		try {
			this.administratorService.findByPrincipal();
			final Actor actor = this.actorService.findOne(actorId);
			actor.setBanned(false);
			this.actorService.save(actor);
			actors = this.actorService.findAll();
			result = new ModelAndView("actor/list");
			result.addObject("requestURI", "actor/administrator/ban.do");
			result.addObject("actors", actors);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
}
