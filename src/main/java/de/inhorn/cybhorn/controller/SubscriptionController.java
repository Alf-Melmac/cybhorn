package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.assembler.SubscriptionAssembler;
import de.inhorn.cybhorn.model.dtos.SubscriptionDto;
import de.inhorn.cybhorn.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alf
 * @since 17.03.2021
 */
@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriptionController {
	private final SubscriptionRepository subscriptionRepository;

	@GetMapping("/list")
	public List<SubscriptionDto> getAllSubscribers() {
		return subscriptionRepository.findAll().stream().map(SubscriptionAssembler::toDto).collect(Collectors.toList());
	}
}
