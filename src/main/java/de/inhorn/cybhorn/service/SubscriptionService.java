package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.assembler.SubscriptionAssembler;
import de.inhorn.cybhorn.exception.ResourceNotFoundException;
import de.inhorn.cybhorn.model.Subscription;
import de.inhorn.cybhorn.model.dtos.SubscriptionDto;
import de.inhorn.cybhorn.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;

	public Subscription createSubscription(SubscriptionDto dto) {
		return subscriptionRepository.save(SubscriptionAssembler.fromDto(dto));
	}

	public List<Subscription> findAllOrdered() {
		return subscriptionRepository.findAll(Sort.by("name"));
	}

	public Subscription findById(long id) {
		return subscriptionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}
}
