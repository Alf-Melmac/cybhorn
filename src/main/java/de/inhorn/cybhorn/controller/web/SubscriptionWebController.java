package de.inhorn.cybhorn.controller.web;

import de.inhorn.cybhorn.controller.SubscriptionController;
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
@RequestMapping("/subscriptions")
public class SubscriptionWebController {
	@GetMapping
	public ModelAndView getSubscriptions(@RequestParam(required = false, defaultValue = "") String filter) {
		ModelAndView mav = new ModelAndView("subscriptions");
		mav.addObject("searchParam", filter);
		mav.addObject("wizardUrl", linkTo(methodOn(SubscriptionWebController.class).getSubscriptionWizard()).toUri().toString());
		mav.addObject("deleteUrl", linkTo(methodOn(SubscriptionController.class).deleteSubscription(Long.MAX_VALUE))
				.toUri()
				.toString()
		.replace(Long.toString(Long.MAX_VALUE), "{id}"));
		return mav;
	}

	@GetMapping("/new")
	public ModelAndView getSubscriptionWizard() {
		final ModelAndView mav = new ModelAndView("subscriptionWizard");
		mav.addObject("saveUrl", linkTo(methodOn(SubscriptionController.class).postSubscription(null)).toUri().toString());
		return mav;
	}
}
