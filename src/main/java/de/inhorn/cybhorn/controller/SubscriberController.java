package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.assembler.SubscriberAssembler;
import de.inhorn.cybhorn.model.dtos.SubscriberViewDto;
import de.inhorn.cybhorn.repository.SubscriberRepository;
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
@RequestMapping("/subscribers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriberController {
	private final SubscriberRepository subscriberRepository;

	@GetMapping("/list")
	public List<SubscriberViewDto> getAllSubscribers() {
		return subscriberRepository.findAll().stream().map(SubscriberAssembler::toDto).collect(Collectors.toList());
	}
}
