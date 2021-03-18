package de.inhorn.cybhorn.controller.web;

import de.inhorn.cybhorn.controller.TerminalController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Controller
@RequestMapping("/terminals")
public class TerminalWebController {
	@GetMapping
	public ModelAndView getTerminals(@RequestParam(required = false, defaultValue = "") String filter) {
		ModelAndView mav = new ModelAndView("terminals");
		mav.addObject("searchParam", filter);
		mav.addObject("wizardUrl", linkTo(methodOn(TerminalWebController.class).getTerminalWizard()).toUri().toString());
		mav.addObject("deleteUrl", linkTo(methodOn(TerminalController.class).deleteTerminal(Long.MAX_VALUE))
				.toUri()
				.toString()
		.replace(Long.toString(Long.MAX_VALUE), "{id}"));
		return mav;
	}

	@GetMapping("/new")
	public ModelAndView getTerminalWizard() {
		ModelAndView mav = new ModelAndView("terminalWizard");
		mav.addObject("saveUrl", linkTo(methodOn(TerminalController.class).postTerminal(null)).toUri().toString());
		return mav;
	}
}
