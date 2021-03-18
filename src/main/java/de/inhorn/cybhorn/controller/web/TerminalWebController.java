package de.inhorn.cybhorn.controller.web;

import de.inhorn.cybhorn.controller.TerminalController;
import de.inhorn.cybhorn.service.TerminalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TerminalWebController {
	private final TerminalService terminalService;

	@GetMapping
	public ModelAndView getTerminals(@RequestParam(required = false, defaultValue = "") String filter) {
		ModelAndView mav = new ModelAndView("terminals");
		mav.addObject("searchParam", filter);
		mav.addObject("wizardUrl", linkTo(methodOn(TerminalWebController.class).getTerminalWizard()).toUri().toString());
		mav.addObject("deleteUrl", linkTo(methodOn(TerminalController.class).deleteTerminal(Long.MAX_VALUE))
				.toUri()
				.toString()
		.replace(Long.toString(Long.MAX_VALUE), "{id}"));
		mav.addObject("editUrl", linkTo(methodOn(TerminalController.class).updateTerminal(Long.MAX_VALUE, null))
				.toUri()
				.toString()
				.replace(Long.toString(Long.MAX_VALUE), "{id}"));
		return mav;
	}

	@GetMapping("/new")
	public ModelAndView getTerminalWizard() {
		ModelAndView mav = new ModelAndView("terminalWizard");
		mav.addObject("saveUrl", linkTo(methodOn(TerminalController.class).postTerminal(null)).toUri().toString());
		mav.addObject("overviewUrl", linkTo(methodOn(TerminalWebController.class).getTerminals("")).toUri().toString());
		return mav;
	}

	@GetMapping("/{id}")
	public ModelAndView getTerminalEdit(@PathVariable long id) {
		ModelAndView mav = new ModelAndView("terminalEdit");
		mav.addObject("terminal", terminalService.findById(id));
		mav.addObject("saveUrl", linkTo(methodOn(TerminalController.class).updateTerminal(id, null)).toUri().toString());
		mav.addObject("overviewUrl", linkTo(methodOn(TerminalWebController.class).getTerminals("")).toUri().toString());
		return mav;
	}
}
