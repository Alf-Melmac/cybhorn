package de.inhorn.cybhorn.controller.web;

import de.inhorn.cybhorn.controller.SubscriptionController;
import de.inhorn.cybhorn.service.SubscriptionService;
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
@RequestMapping("/subscriptions")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriptionWebController {
	private final SubscriptionService subscriptionService;

	@GetMapping
	public ModelAndView getSubscriptions(@RequestParam(required = false, defaultValue = "") String filter) {
		ModelAndView mav = new ModelAndView("subscriptions");
		mav.addObject("searchParam", filter);
		mav.addObject("wizardUrl", linkTo(methodOn(SubscriptionWebController.class).getSubscriptionWizard()).toUri().toString());
		mav.addObject("deleteUrl", linkTo(methodOn(SubscriptionController.class).deleteSubscription(Long.MAX_VALUE))
				.toUri()
				.toString()
				.replace(Long.toString(Long.MAX_VALUE), "{id}"));
		mav.addObject("editUrl", linkTo(methodOn(SubscriptionController.class).updateSubscription(Long.MAX_VALUE, null))
				.toUri()
				.toString()
				.replace(Long.toString(Long.MAX_VALUE), "{id}"));
		return mav;
	}

	@GetMapping("/new")
	public ModelAndView getSubscriptionWizard() {
		final ModelAndView mav = new ModelAndView("subscriptionWizard");
		mav.addObject("saveUrl", linkTo(methodOn(SubscriptionController.class).postSubscription(null)).toUri().toString());
		mav.addObject("overviewUrl", linkTo(methodOn(SubscriptionWebController.class).getSubscriptions("")).toUri().toString());
		return mav;
	}

	@GetMapping("/{id}")
	public ModelAndView getSubscriberEdit(@PathVariable long id) {
		final ModelAndView mav = new ModelAndView("subscriptionEdit");
		mav.addObject("subscription", subscriptionService.findById(id));
		mav.addObject("saveUrl", linkTo(methodOn(SubscriptionController.class).updateSubscription(id, null)).toUri().toString());
		mav.addObject("overviewUrl", linkTo(methodOn(SubscriptionWebController.class).getSubscriptions("")).toUri().toString());
		return mav;
	}
}
