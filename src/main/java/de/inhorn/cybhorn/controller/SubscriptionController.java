package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.assembler.SubscriptionAssembler;
import de.inhorn.cybhorn.model.dtos.SubscriptionDto;
import de.inhorn.cybhorn.repository.SubscriptionRepository;
import de.inhorn.cybhorn.service.SubscriptionService;
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
@RequestMapping("/subscriptions")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriptionController {
	private final SubscriptionRepository subscriptionRepository;
	private final SubscriptionService subscriptionService;

	@GetMapping("/list")
	public List<SubscriptionDto> getAllSubscribers() {
		return SubscriptionAssembler.toDtoList(subscriptionRepository.findAll());
	}

	@PostMapping
	public SubscriptionDto postSubscription(@Valid @RequestBody SubscriptionDto subscription) {
		return SubscriptionAssembler.toDto(subscriptionService.createSubscription(subscription));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubscription(@PathVariable long id) {
		subscriptionRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
