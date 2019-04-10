
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CompanyService;
import services.HackerService;
import services.PositionService;
import controllers.AbstractController;
import domain.Company;
import domain.Hacker;
import domain.Position;

@Controller
@RequestMapping("/stats/administrator")
public class StatsAdministratorController extends AbstractController {

	@Autowired
	private PositionService		positionService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private HackerService		hackerService;


	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		result = new ModelAndView("stats/display");

		try {
			final Collection<Double> positionsPerCompany = this.positionService.statsPositionsPerCompany();
			final Collection<Double> applicationsPerHacker = this.applicationService.statsApplicationsPerHacker();
			final Collection<Company> companiesHaveOfferedMorePositions = this.companyService.companiesHaveOfferedMorePositions();
			final Collection<Hacker> hackersHaveMadeMoreApplications = this.hackerService.hackersHaveMadeMoreApplications();
			final Collection<Double> salaryOfferedPerPosition = this.positionService.statsSalaryOfferedPerPosition();
			final Collection<Position> worstPositionsSalary = this.positionService.worstPositionsSalary();
			final Collection<Position> bestPositionsSalary = this.positionService.bestPositionsSalary();
			result.addObject("worstPositionsSalary", worstPositionsSalary);
			result.addObject("bestPositionsSalary", bestPositionsSalary);
			result.addObject("salaryOfferedPerPosition", salaryOfferedPerPosition);
			result.addObject("hackersHaveMadeMoreApplications", hackersHaveMadeMoreApplications);
			result.addObject("companiesHaveOfferedMorePositions", companiesHaveOfferedMorePositions);
			result.addObject("applicationsPerHacker", applicationsPerHacker);
			result.addObject("positionsPerCompany", positionsPerCompany);
			result.addObject("requestURI", "/stats/administrator/display.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");

		}
		return result;
	}
}
