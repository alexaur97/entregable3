
package controllers.hacker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.HackerService;
import services.PositionDataService;
import domain.Curriculum;
import domain.PositionData;

@Controller
@RequestMapping("/positionData/hacker/")
public class PositionDataHackerController {

	@Autowired
	private PositionDataService	positionDataService;

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private CurriculumService	curriculumService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		PositionData positionData;
		positionData = this.positionDataService.create();

		try {
			this.hackerService.findByPrincipal();
			positionData.setId(0);

			result = this.createEditModelAndView(positionData);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int positionDataId) {
		ModelAndView res;
		try {

			final PositionData positionData = this.positionDataService.findOne(positionDataId);
			Assert.notNull(positionData);
			final Integer idH = this.hackerService.findByPrincipal().getId();
			final Collection<Curriculum> curriculums = this.curriculumService.findByHacker(idH);
			final Curriculum curriculum = this.curriculumService.findByPositionData(positionDataId);
			Assert.isTrue(curriculums.contains(curriculum));
			res = this.createEditModelAndView(positionData);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("positionData") final PositionData positionData, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(positionData);
		else
			try {
				this.positionDataService.save(positionData);
				res = new ModelAndView("redirect:/curriculum/hacker/list.do");

			} catch (final Throwable oops) {

				res = this.createEditModelAndView(positionData, "positionData.commit.error");

			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final PositionData positionData) {
		return this.createEditModelAndView(positionData, null);
	}
	protected ModelAndView createEditModelAndView(final PositionData positionData, final String messageCode) {
		final ModelAndView res;
		res = new ModelAndView("positionData/edit");
		res.addObject("positionData", positionData);
		res.addObject("message", messageCode);

		return res;
	}

}
