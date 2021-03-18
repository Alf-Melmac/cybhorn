package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.model.dtos.SessionDto;
import de.inhorn.cybhorn.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private final SessionService sessionService;

	@PostMapping
	public ResponseEntity<Void> postSession(@Valid @RequestBody SessionDto session) {
		sessionService.bookSession(session);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
