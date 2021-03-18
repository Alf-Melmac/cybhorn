package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.assembler.SubscriberAssembler;
import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.dtos.SubscriberPostDto;
import de.inhorn.cybhorn.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriberService {
	private final SubscriberRepository subscriberRepository;
	private final SubscriberAssembler subscriberAssembler;

	public Subscriber createSubscriber(SubscriberPostDto dto) {
		final Subscriber subscriber = subscriberAssembler.fromDto(dto);

		return subscriberRepository.save(subscriber);
	}
}
