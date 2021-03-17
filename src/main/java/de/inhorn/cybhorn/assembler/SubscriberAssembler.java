package de.inhorn.cybhorn.assembler;

import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.dtos.SubscriberPostDto;
import de.inhorn.cybhorn.model.dtos.SubscriberViewDto;
import org.springframework.stereotype.Component;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Component
public class SubscriberAssembler {
	public static Subscriber fromDto(SubscriberPostDto subscriberPostDto) {
		if (subscriberPostDto == null) {
			return null;
		}

		return Subscriber.builder()
				.mcc(Integer.parseInt(subscriberPostDto.getMcc()))
				.mnc(Integer.parseInt(subscriberPostDto.getMnc()))
				.msin(Integer.parseInt(subscriberPostDto.getMsin()))
				.terminal(TerminalAssembler.fromDto(subscriberPostDto.getTerminal()))
				.subscription(SubscriptionAssembler.fromDto(subscriberPostDto.getSubscription()))
				.secondsCalled(subscriberPostDto.getSecondsCalled())
				.dataUsed(subscriberPostDto.getDataUsed())
				.build();
	}

	public static SubscriberViewDto toDto(Subscriber subscriber) {
		return SubscriberViewDto.builder()
				.imsi(Long.toString(subscriber.getImsi()))
				.terminal(TerminalAssembler.toDto(subscriber.getTerminal()))
				.subscription(SubscriptionAssembler.toDto(subscriber.getSubscription()))
				.secondsCalled(subscriber.getSecondsCalled())
				.dataUsed(subscriber.getDataUsed())
				.build();
	}
}
