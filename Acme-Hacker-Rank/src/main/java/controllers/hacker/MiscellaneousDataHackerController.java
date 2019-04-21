
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
import services.HackerService;
import services.MiscellaniusDataService;
import domain.Curriculum;
import domain.MiscellaniusData;

@Controller
@RequestMapping("/miscellaneousData/hacker/")
public class MiscellaneousDataHackerController {

	@Autowired
	private MiscellaniusDataService	miscellaneousDataService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private CurriculumService		curriculumService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam("curriculumId") final int curriculumId) {

		ModelAndView result;
		MiscellaniusData miscellaneousData;
		miscellaneousData = this.miscellaneousDataService.create();
		final Curriculum c = this.curriculumService.findOne(curriculumId);

		try {
			this.hackerService.findByPrincipal();
			miscellaneousData.setId(0);

			result = new ModelAndView("miscellaneousData/edit");
			result.addObject("miscellaniusData", miscellaneousData);
			result.addObject("curriculum", c);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int miscellaneousDataId) {
		ModelAndView res;
		try {

			final MiscellaniusData miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);
			Assert.notNull(miscellaneousData);
			final Integer idH = this.hackerService.findByPrincipal().getId();
			final Collection<Curriculum> curriculums = this.curriculumService.findByHacker(idH);
			final Curriculum curriculum = this.curriculumService.findByMiscellaneousData(miscellaneousData);
			Assert.isTrue(curriculums.contains(curriculum));
			res = this.createEditModelAndView(miscellaneousData);

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/#");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MiscellaniusData miscellaneousData, @RequestParam("curriculumId") final int curriculumId, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(miscellaneousData);
		else
			try {

				this.miscellaneousDataService.save(miscellaneousData);

				if (miscellaneousData.getId() == 0) {
					final Curriculum c = this.curriculumService.findOne(curriculumId);
					this.curriculumService.saveMiscellaneousData(miscellaneousData, c);
				}

				res = new ModelAndView("redirect:/curriculum/hacker/list.do");

			} catch (final Throwable oops) {

				res = this.createEditModelAndView(miscellaneousData, "miscellaneousData.commit.error");

			}

		return res;
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MiscellaniusData miscellaneousData, final BindingResult binding) {
		ModelAndView result;
		final MiscellaniusData misc = this.miscellaneousDataService.findOne(miscellaneousData.getId());
		try {

			this.curriculumService.deleteMiscellaneousData(misc);
			this.miscellaneousDataService.delete(misc);

			result = new ModelAndView("redirect:/curriculum/hacker/list.do");

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(misc, oops.getMessage());

			final String msg = oops.getMessage();
			if (msg.equals("miscellaneousDatacannotDelete")) {
				final Boolean miscellaneousDatacannotDelete = true;
				result.addObject("miscellaneousDatacannotDelete", miscellaneousDatacannotDelete);

			}
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int miscellaneousDataId) {
		ModelAndView result;
		final MiscellaniusData miscellaneousData;

		try {
			this.hackerService.findByPrincipal();
			Assert.notNull(miscellaneousDataId);
			miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);

			final Curriculum cu = this.curriculumService.findByMiscellaneousData(miscellaneousData);

			result = new ModelAndView("miscellaneousData/show");
			result.addObject("miscellaniusData", miscellaneousData);
			result.addObject("curriculum", cu);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final MiscellaniusData miscellaneousData) {
		return this.createEditModelAndView(miscellaneousData, null);
	}
	protected ModelAndView createEditModelAndView(final MiscellaniusData miscellaneousData, final String messageCode) {
		final ModelAndView res;
		final Curriculum cu = this.curriculumService.findByMiscellaneousData(miscellaneousData);

		res = new ModelAndView("miscellaneousData/edit");
		res.addObject("miscellaniusData", miscellaneousData);
		res.addObject("message", messageCode);
		res.addObject("curriculum", cu);

		return res;
	}

}
