
package controllers.all;

import java.util.Collection;
import java.util.Locale;

import javax.validation.Valid;

import miscellaneous.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.HackerService;
import domain.Hacker;
import forms.HackerRegisterForm;

@Controller
@RequestMapping("/hacker")
public class HackerController {

	@Autowired
	private HackerService	hackerService;

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final HackerRegisterForm hackerRegisterForm = new HackerRegisterForm();
			result = this.createEditModelAndView(hackerRegisterForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final HackerRegisterForm hackerRegisterForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(hackerRegisterForm);
		else
			try {
				final Hacker hacker = this.hackerService.constructByForm(hackerRegisterForm);
				final Hacker saved = this.hackerService.save(hacker);
				System.out.println(saved);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(hackerRegisterForm);

				final Collection<String> accounts = this.actorService.findAllAccounts();
				final Collection<String> emails = this.actorService.findAllEmails();

				if (accounts.contains(hackerRegisterForm.getUsername()))
					result.addObject("message", "hacker.username.error");
				else if (emails.contains(hackerRegisterForm.getEmail()))
					result.addObject("message", "hacker.email.error");
				else if (!hackerRegisterForm.getConfirmPassword().equals(hackerRegisterForm.getPassword()))
					result.addObject("message", "hacker.password.error");
				else if (Utils.creditCardIsExpired(hackerRegisterForm.getExpirationMonth(), hackerRegisterForm.getExpirationYear()))
					result.addObject("message", "company.expired.card.error");
				else
					result.addObject("message", "hacker.commit.error");
			}
		return result;
	}
	protected ModelAndView createEditModelAndView(final HackerRegisterForm hackerRegisterForm) {
		return this.createEditModelAndView(hackerRegisterForm, null);
	}

	protected ModelAndView createEditModelAndView(final HackerRegisterForm hackerRegisterForm, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("hacker/signup");
		result.addObject("hackerRegisterForm", hackerRegisterForm);
		result.addObject("message", messageCode);

		final Locale l = LocaleContextHolder.getLocale();
		final String lang = l.getLanguage();
		result.addObject("lang", lang);

		return result;
	}
}
