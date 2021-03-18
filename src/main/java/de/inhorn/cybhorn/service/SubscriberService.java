package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.assembler.SubscriberAssembler;
import de.inhorn.cybhorn.exception.ResourceNotFoundException;
import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.dtos.SubscriberEditDto;
import de.inhorn.cybhorn.model.dtos.SubscriberPostDto;
import de.inhorn.cybhorn.repository.SubscriberRepository;
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
public class SubscriberService {
	private final SubscriberRepository subscriberRepository;
	private final SubscriberAssembler subscriberAssembler;
	private final TerminalService terminalService;
	private final SubscriptionService subscriptionService;

	public List<Subscriber> findAll() {
		return subscriberRepository.findAll();
	}

	/**
	 * @return alls subscribers ordered by last name
	 */
	public List<Subscriber> findAllOrdered() {
		return subscriberRepository.findAll(Sort.by("lastName"));
	}

	public Subscriber findByImsi(long imsi) {
		return subscriberRepository.findById(imsi).orElseThrow(ResourceNotFoundException::new);
	}

	public Subscriber createSubscriber(SubscriberPostDto dto) {
		final Subscriber subscriber = subscriberAssembler.fromDto(dto);

		return subscriberRepository.save(subscriber);
	}

	/**
	 * Updates the {@link Subscriber} with the given values from the dto, if present. Found by id of dto
	 *
	 * @param dto with id and values to update
	 * @return updated subscriber
	 */
	public Subscriber updateSubscriber(@NonNull SubscriberEditDto dto) {
		Subscriber subscriber = findByImsi(dto.getImsi());

		DtoUtils.ifPresent(dto.getFirstName(), subscriber::setFirstName);
		DtoUtils.ifPresent(dto.getLastName(), subscriber::setLastName);
		DtoUtils.ifPresent(dto.getTerminalId(), terminal -> subscriber.setTerminal(terminalService.findById(terminal)));
		DtoUtils.ifPresent(dto.getSubscriptionId(), subscription -> subscriber.setSubscription(subscriptionService.findById(subscription)));

		return subscriber;
	}

	/**
	 * Uses the {@link Subscriber#reset()} function to Reset all consumption values for the given subscriber
	 *
	 * @param imsi of the subscriber to reset
	 * @return reset subscriber
	 */
	public Subscriber resetSubscriber(long imsi) {
		Subscriber subscriber = findByImsi(imsi);
		subscriber.reset();
		return subscriber;
	}

	public void deleteById(long id) {
		subscriberRepository.deleteById(id);
	}
}
