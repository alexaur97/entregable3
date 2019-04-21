
package controllers.hacker;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.EducationDataService;
import services.HackerService;
import domain.Curriculum;
import domain.EducationData;

@Controller
@RequestMapping("/educationData/hacker/")
public class EducationDataHackerController {

	@Autowired
	private EducationDataService	educationDataService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private CurriculumService		curriculumService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		EducationData educationData;
		educationData = this.educationDataService.create();

		try {
			this.hackerService.findByPrincipal();
			educationData.setId(0);

			result = this.createEditModelAndView(educationData);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int educationDataId) {
		ModelAndView res;
		try {

			final EducationData educationData = this.educationDataService.findOne(educationDataId);
			Assert.notNull(educationData);
			final Integer idH = this.hackerService.findByPrincipal().getId();
			final Collection<Curriculum> curriculums = this.curriculumService.findByHacker(idH);
			final Curriculum curriculum = this.curriculumService.findByEducationData(educationData);
			Assert.isTrue(curriculums.contains(curriculum));
			res = this.createEditModelAndView(educationData);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EducationData educationData, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(educationData);
		else
			try {

				Assert.isTrue(educationData.getStartDate().before(educationData.getEndDate()));

				this.educationDataService.save(educationData);

				if (educationData.getId() == 0)
					this.curriculumService.saveEducationData(educationData);

				res = new ModelAndView("redirect:/curriculum/hacker/list.do");

			} catch (final Throwable oops) {

				if (educationData.getStartDate().after(educationData.getEndDate()))
					res = this.createEditModelAndView(educationData, "educationData.error.date2");

				else
					res = this.createEditModelAndView(educationData, "educationData.commit.error");

			}

		return res;
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final EducationData educationData, final BindingResult binding) {
		ModelAndView result;
		final EducationData ed = this.educationDataService.findOne(educationData.getId());
		try {

			this.curriculumService.deleteEductationData(ed);
			this.educationDataService.delete(ed);

			result = new ModelAndView("redirect:/curriculum/hacker/list.do");

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(ed, oops.getMessage());

			final String msg = oops.getMessage();
			if (msg.equals("educationDatacannotDelete")) {
				final Boolean educationDatacannotDelete = true;
				result.addObject("educationDatacannotDelete", educationDatacannotDelete);

			}
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int educationDataId) {
		ModelAndView result;
		final EducationData educationData;

		try {
			this.hackerService.findByPrincipal();
			Assert.notNull(educationDataId);
			educationData = this.educationDataService.findOne(educationDataId);

			result = new ModelAndView("educationData/show");
			result.addObject("educationData", educationData);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final EducationData educationData) {
		return this.createEditModelAndView(educationData, null);
	}
	protected ModelAndView createEditModelAndView(final EducationData educationData, final String messageCode) {
		final ModelAndView res;
		res = new ModelAndView("educationData/edit");
		res.addObject("educationData", educationData);
		res.addObject("message", messageCode);

		return res;
	}

}
