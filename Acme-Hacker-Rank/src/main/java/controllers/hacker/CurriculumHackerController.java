
package controllers.hacker;

import java.util.Collection;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import domain.Curriculum;
import forms.CurriculumCreateForm;

@Controller
@RequestMapping("/curriculum/hacker")
public class CurriculumHackerController {

	@Autowired
	private CurriculumService	curriculumService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Collection<Curriculum> curriculums = this.curriculumService.findAllByPrincipal();
			result = new ModelAndView("curriculum/list");
			result.addObject("curriculums", curriculums);
			result.addObject("requestURI", "/curriculum/hacker/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final CurriculumCreateForm curriculumCreateForm = new CurriculumCreateForm();
			result = this.createModelAndView(curriculumCreateForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int curriculumId) {
		ModelAndView result;
		try {
			final Curriculum curriculum = this.curriculumService.findOne(curriculumId);
			result = new ModelAndView("curriculum/show");
			result.addObject("curriculum", curriculum);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView create(@Valid final CurriculumCreateForm curriculumCreateForm, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			result = this.createModelAndView(curriculumCreateForm);
		else
			try {
				final Curriculum curriculum = this.curriculumService.constructByForm(curriculumCreateForm);
				final Curriculum saved = this.curriculumService.save(curriculum);
				result = new ModelAndView("redirect:/curriculum/hacker/show.do?curriculumId=" + saved.getId());
			} catch (final Throwable oops) {
				result = this.createModelAndView(curriculumCreateForm, "curriculum.commit.error");
			}
		return result;
	}

	protected ModelAndView createModelAndView(final CurriculumCreateForm curriculumCreateForm) {
		return this.createModelAndView(curriculumCreateForm, null);
	}

	protected ModelAndView createModelAndView(final CurriculumCreateForm curriculumCreateForm, final String messageCode) {
		final ModelAndView result = new ModelAndView("curriculum/create");
		result.addObject("curriculumCreateForm", curriculumCreateForm);
		result.addObject("message", messageCode);
		final Locale l = LocaleContextHolder.getLocale();
		final String lang = l.getLanguage();
		result.addObject("lang", lang);
		return result;
	}

}
