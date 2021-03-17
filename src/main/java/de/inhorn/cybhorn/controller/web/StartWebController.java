package de.inhorn.cybhorn.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Controller
public class StartWebController {
	@GetMapping("/")
	public ModelAndView getStart() {
		ModelAndView mav = new ModelAndView("start");

		mav.addObject("subscribersUrl", linkTo(methodOn(SubscriberWebController.class).getSubscribers()));
		mav.addObject("subscriptionsUrl", linkTo(methodOn(SubscriptionWebController.class).getSubscriptions("")));
		mav.addObject("terminalsUrl", linkTo(methodOn(TerminalWebController.class).getTerminals("")));

		return mav;
	}
}
