package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.exception.BusinessRuntimeException;
import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.dtos.SessionDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static de.inhorn.cybhorn.model.enums.ServiceType.CALL;

/**
 * @author Alf
 * @since 18.03.2021
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SessionService {
	private final SubscriberService subscriberService;
	private final TerminalService terminalService;

	/**
	 * Processes the given {@link SessionDto}
	 *
	 * @param session to book
	 * @throws BusinessRuntimeException if the session is not postable
	 */
	public void bookSession(@NonNull SessionDto session) {
		final Subscriber subscriber = subscriberService.findByImsi(session.getImsi());

		if (session.getServiceType() == CALL) {
			subscriber.addSecondsCalled(session.getDuration());
			return;
		}

		double maxThroughput = terminalService.calculateMaxThroughput(subscriber.getTerminal());
		if (maxThroughput >= session.getServiceType().getRequiredDataRate()) {


			subscriber.useData(session.getServiceType().getRequiredDataRate() / 8 * session.getDuration());
		} else {
			throw BusinessRuntimeException.builder().title("Connection too slow, go up a mountain. \n Required: " + session.getServiceType().getRequiredDataRate() + "MB    Your connection: " + maxThroughput*8 + "MB").build();
		}
	}
}
