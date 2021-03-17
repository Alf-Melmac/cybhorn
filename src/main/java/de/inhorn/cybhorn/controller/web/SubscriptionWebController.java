package de.inhorn.cybhorn.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Controller
@RequestMapping("/subscriptions")
public class SubscriptionWebController {
	@GetMapping
	public ModelAndView getSubscriptions(@RequestParam(required = false, defaultValue = "") String filter) {
		ModelAndView mav = new ModelAndView("subscriptions");
		mav.addObject("searchParam", filter);
		return mav;
	}
}
