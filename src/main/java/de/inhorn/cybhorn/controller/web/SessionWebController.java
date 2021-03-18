package de.inhorn.cybhorn.controller.web;

import de.inhorn.cybhorn.controller.SessionController;
import de.inhorn.cybhorn.service.SessionService;
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
 * @since 18.03.2021
 */
@Controller
@RequestMapping("/sessions")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SessionWebController {
	private final SessionService sessionService;

	@GetMapping("/new")
	public ModelAndView getSessionWizard() {
		final ModelAndView mav = new ModelAndView("sessionWizard");
		mav.addObject("saveUrl", linkTo(methodOn(SessionController.class).postSession(null)).toUri().toString());
		mav.addObject("overviewUrl", linkTo(methodOn(StartWebController.class).getStart()).toUri().toString());
		return mav;
	}
}
