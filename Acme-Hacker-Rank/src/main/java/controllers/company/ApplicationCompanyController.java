
package controllers.company;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CompanyService;
import controllers.AbstractController;
import domain.Application;
import domain.Company;

@Controller
@RequestMapping("/application/company/")
public class ApplicationCompanyController extends AbstractController {

	//Repository
	@Autowired
	private CompanyService		companyService;

	//Servicios

	@Autowired
	private ApplicationService	applicationService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Company company = this.companyService.findByPrincipal();
			final Collection<Application> applications;
			applications = this.applicationService.findApplicationsByCompany(company.getId());
			result = new ModelAndView("application/list");
			result.addObject("requestURI", "application/list.do");
			result.addObject("applications", applications);
			final String s = "SUBMITTED";
			result.addObject("s", s);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int applicationId) {
		ModelAndView result;
		final Collection<Application> applications;
		final String s = "SUBMITTED";
		try {
			final Application application = this.applicationService.acceptApplicationChanges(applicationId);
			this.applicationService.saveCompany(application);
			final Company company = this.companyService.findByPrincipal();
			applications = this.applicationService.findApplicationsByCompany(company.getId());

			result = new ModelAndView("application/list");
			result.addObject("requestURI", "application/accept.do");
			result.addObject("applications", applications);
			result.addObject("s", s);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView reject(@RequestParam final int applicationId) {
		ModelAndView result;
		final Collection<Application> applications;
		final String s = "SUBMITTED";
		try {
			final Application application = this.applicationService.rejectApplicationChanges(applicationId);
			this.applicationService.saveCompany(application);
			final Company company = this.companyService.findByPrincipal();
			applications = this.applicationService.findApplicationsByCompany(company.getId());

			result = new ModelAndView("application/list");
			result.addObject("requestURI", "application/accept.do");
			result.addObject("applications", applications);
			result.addObject("s", s);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

}
