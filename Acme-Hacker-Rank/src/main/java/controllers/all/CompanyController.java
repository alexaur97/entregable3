
package controllers.all;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import domain.Company;
import forms.CompanyRegisterForm;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService	companyService;


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final CompanyRegisterForm companyRegisterForm = new CompanyRegisterForm();
			result = this.createEditModelAndView(companyRegisterForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CompanyRegisterForm companyRegisterForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(companyRegisterForm);
		else
			try {
				final Company company = this.companyService.constructByForm(companyRegisterForm);
				this.companyService.save(company);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("redirect:/#");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final CompanyRegisterForm companyRegisterForm) {
		return this.createEditModelAndView(companyRegisterForm, null);
	}

	protected ModelAndView createEditModelAndView(final CompanyRegisterForm companyRegisterForm, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("company/signup");
		result.addObject("companyRegisterForm", companyRegisterForm);
		result.addObject("message", messageCode);
		return result;
	}
}
