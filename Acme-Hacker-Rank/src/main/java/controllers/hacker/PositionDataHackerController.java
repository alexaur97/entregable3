
package controllers.hacker;

import java.util.Collection;
import java.util.Date;

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
	public ModelAndView save(@Valid final PositionData positionData, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(positionData);
		else
			try {
				final Date date = new Date();
				Assert.isTrue(positionData.getEndDate().after(date));
				Assert.isTrue(positionData.getStartDate().before(positionData.getEndDate()));

				this.positionDataService.save(positionData);

				if (positionData.getId() == 0)
					this.curriculumService.savePositionData(positionData);

				res = new ModelAndView("redirect:/curriculum/hacker/list.do");

			} catch (final Throwable oops) {

				if (!positionData.getEndDate().after(new Date()))
					res = this.createEditModelAndView(positionData, "positionData.error.date");

				else if (positionData.getStartDate().after(positionData.getEndDate()))
					res = this.createEditModelAndView(positionData, "positionData.error.date2");

				res = this.createEditModelAndView(positionData, "positionData.commit.error");

			}

		return res;
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final PositionData positionData, final BindingResult binding) {
		ModelAndView result;
		final PositionData pos = this.positionDataService.findOne(positionData.getId());
		try {

			this.curriculumService.deletePositionData(pos);
			this.positionDataService.delete(pos);

			result = new ModelAndView("redirect:/curriculum/hacker/list.do");

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(pos, oops.getMessage());

			final String msg = oops.getMessage();
			if (msg.equals("positionDatacannotDelete")) {
				final Boolean positionDatacannotDelete = true;
				result.addObject("positionDatacannotDelete", positionDatacannotDelete);

			}
		}
		return result;
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

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int positionDataId) {
		ModelAndView result;
		final PositionData positionData;

		try {
			this.hackerService.findByPrincipal();
			Assert.notNull(positionDataId);
			positionData = this.positionDataService.findOne(positionDataId);

			result = new ModelAndView("positionData/show");
			result.addObject("positionData", positionData);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

}
