package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.assembler.SubscriberAssembler;
import de.inhorn.cybhorn.model.dtos.SubscriberEditDto;
import de.inhorn.cybhorn.model.dtos.SubscriberPostDto;
import de.inhorn.cybhorn.model.dtos.SubscriberViewDto;
import de.inhorn.cybhorn.repository.SubscriberRepository;
import de.inhorn.cybhorn.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Alf
 * @since 17.03.2021
 */
@RestController
@RequestMapping("/subscribers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriberController {
	private final SubscriberRepository subscriberRepository;
	private final SubscriberService subscriberService;

	@GetMapping("/list")
	public List<SubscriberViewDto> getAllSubscribers() {
		return SubscriberAssembler.toDtoList(subscriberRepository.findAll());
	}

	@PostMapping
	public SubscriberViewDto postSubscriber(@Valid @RequestBody SubscriberPostDto subscriber) {
		return SubscriberAssembler.toDto(subscriberService.createSubscriber(subscriber));
	}

	@PutMapping("/{id}")
	public SubscriberViewDto updateSubscriber(@PathVariable long id, @Valid @RequestBody SubscriberEditDto subscriber) {
		subscriber.setImsi(id);
		return SubscriberAssembler.toDto(subscriberService.updateSubscriber(subscriber));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubscriber(@PathVariable long id) {
		subscriberRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
