package de.inhorn.cybhorn.controller.web;

import de.inhorn.cybhorn.assembler.SubscriptionAssembler;
import de.inhorn.cybhorn.assembler.TerminalAssembler;
import de.inhorn.cybhorn.controller.SubscriberController;
import de.inhorn.cybhorn.service.SubscriptionService;
import de.inhorn.cybhorn.service.TerminalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Controller
@RequestMapping("/subscribers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriberWebController {
	private final TerminalService terminalService;
	private final SubscriptionService subscriptionService;

	@GetMapping
	public ModelAndView getSubscribers() {
		ModelAndView mav = new ModelAndView("subscribers");
		mav.addObject("wizardUrl", linkTo(methodOn(SubscriberWebController.class).getSubscriberWizard()).toUri().toString());
		mav.addObject("deleteUrl", linkTo(methodOn(SubscriberController.class).deleteSubscriber(Long.MAX_VALUE))
				.toUri()
				.toString()
		.replace(Long.toString(Long.MAX_VALUE), "{id}"));
		return mav;
	}

	@GetMapping("/new")
	public ModelAndView getSubscriberWizard() {
		final ModelAndView mav = new ModelAndView("subscriberWizard");
		mav.addObject("terminalList", TerminalAssembler.toDtoList(terminalService.findAllOrdered()));
		mav.addObject("subscriptionList", SubscriptionAssembler.toDtoList(subscriptionService.findAllOrdered()));
		mav.addObject("terminalWizardUrl", linkTo(methodOn(TerminalWebController.class).getTerminalWizard()));
		mav.addObject("subscriptionWizardUrl", linkTo(methodOn(SubscriptionWebController.class).getSubscriptionWizard()));
		mav.addObject("saveUrl", linkTo(methodOn(SubscriberController.class).postSubscriber(null)).toUri().toString());
		return mav;
	}
}
