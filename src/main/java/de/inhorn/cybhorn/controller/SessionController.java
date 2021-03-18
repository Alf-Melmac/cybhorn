package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.model.dtos.AbstractIdEntityDto;
import de.inhorn.cybhorn.model.dtos.SubscriberPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Alf
 * @since 18.03.2021
 */
@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SessionController {
	@PostMapping
	public AbstractIdEntityDto postSession(@Valid @RequestBody SubscriberPostDto session) {
		return null; //TODO
	}
}
