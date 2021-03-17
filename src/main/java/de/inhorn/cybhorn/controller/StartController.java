package de.inhorn.cybhorn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Controller
public class StartController {
	@GetMapping("/")
	public ModelAndView getStart() {
		return new ModelAndView("start");
	}
}
