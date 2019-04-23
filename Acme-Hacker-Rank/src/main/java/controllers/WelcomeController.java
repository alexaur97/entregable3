/*
 * WelcomeController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CompanyService;
import services.ConfigurationParametersService;
import services.FinderService;
import services.HackerService;
import domain.Actor;
import domain.Company;
import domain.ConfigurationParameters;
import domain.Hacker;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	//@Autowired
	//FinderService							finderService;

	@Autowired
	ActorService							actorService;

	@Autowired
	HackerService							hackerService;

	@Autowired
	CompanyService							companyService;

	@Autowired
	FinderService							finderService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		final ConfigurationParameters config = this.configurationParametersService.find();
		final String name;

		final Locale l = LocaleContextHolder.getLocale();
		final String lang = l.getLanguage();

		name = config.getName();
		String sysMessage = "";

		if (lang == "en")
			sysMessage = sysMessage + config.getSysMessage();
		else if (lang == "es")
			sysMessage = sysMessage + config.getSysMessageEs();

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		this.finderService.cleanCacheIfNecessary();

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("sysMessage", sysMessage);
		result.addObject("moment", moment);
		result.addObject("lang", lang);
		try {
			final Actor actor = this.actorService.findByPrincipal();
			result.addObject("Banned", actor.getBanned());

		} catch (final Exception e) {
			System.out.println(e);
		}
		try {
			final Hacker hacker = this.hackerService.findByPrincipal();
			result.addObject("hacker", hacker);
			if (hacker.getBanned() == true)
				result = new ModelAndView("redirect:/j_spring_security_logout");

		} catch (final Exception e) {
			System.out.println(e);
		}

		try {
			final Company company = this.companyService.findByPrincipal();
			result.addObject("company", company);
			if (company.getBanned() == true)
				result = new ModelAndView("redirect:/j_spring_security_logout");

		} catch (final Exception e) {
		}

		return result;
	}
}
