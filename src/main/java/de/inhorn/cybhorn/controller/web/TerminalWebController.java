package de.inhorn.cybhorn.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Controller
@RequestMapping("/terminals")
public class TerminalWebController {
	@GetMapping
	public ModelAndView getStart() {
		return new ModelAndView("terminals");
	}
}
