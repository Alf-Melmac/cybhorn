package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.assembler.SubscriptionAssembler;
import de.inhorn.cybhorn.exception.ResourceNotFoundException;
import de.inhorn.cybhorn.model.Subscription;
import de.inhorn.cybhorn.model.dtos.SubscriptionDto;
import de.inhorn.cybhorn.repository.SubscriptionRepository;
import de.inhorn.cybhorn.util.DtoUtils;
import lombok.NonNull;
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

	public Subscription updateSubscription(@NonNull SubscriptionDto dto) {
		Subscription subscription = findById(dto.getId());

		DtoUtils.ifPresent(dto.getName(), subscription::setName);
		DtoUtils.ifPresent(dto.getBasicFee(), subscription::setBasicFee);
		DtoUtils.ifPresent(dto.getSecondsIncluded(), subscription::setSecondsIncluded);
		DtoUtils.ifPresent(dto.getPricePerMinute(), subscription::setPricePerMinute);
		DtoUtils.ifPresentDouble(dto.getDataVolume(), subscription::setDataVolume);

		return subscription;
	}
}
