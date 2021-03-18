package de.inhorn.cybhorn.assembler;

import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.dtos.SubscriberPostDto;
import de.inhorn.cybhorn.model.dtos.SubscriberViewDto;
import de.inhorn.cybhorn.service.SubscriptionService;
import de.inhorn.cybhorn.service.TerminalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriberAssembler {
	private final TerminalService terminalService;
	private final SubscriptionService subscriptionService;

	public Subscriber fromDto(SubscriberPostDto subscriberPostDto) {
		if (subscriberPostDto == null) {
			return null;
		}

		return Subscriber.builder()
				.mcc(Integer.parseInt(subscriberPostDto.getMcc()))
				.mnc(Integer.parseInt(subscriberPostDto.getMnc()))
				.msin(Long.parseLong(subscriberPostDto.getMsin()))
				.firstName(subscriberPostDto.getFirstName())
				.lastName(subscriberPostDto.getLastName())
				.terminal(terminalService.findById(subscriberPostDto.getTerminalId()))
				.subscription(subscriptionService.findById(subscriberPostDto.getSubscriptionId()))
				.secondsCalled(subscriberPostDto.getSecondsCalled())
				.dataUsed(subscriberPostDto.getDataUsed())
				.build();
	}

	public static SubscriberViewDto toDto(Subscriber subscriber) {
		final String firstName = subscriber.getFirstName();
		final String lastName = subscriber.getLastName();
		String name = "";
		if (StringUtils.isEmptyOrWhitespace(firstName)) {
			if (!StringUtils.isEmptyOrWhitespace(lastName)) {
				name = lastName;
			}
		} else if (StringUtils.isEmptyOrWhitespace(lastName)) {
			name = firstName;
		} else {
			name = firstName + " " + lastName;
		}

		return SubscriberViewDto.builder()
				.imsi(Long.toString(subscriber.getImsi()))
				.name(name)
				.terminal(TerminalAssembler.toDto(subscriber.getTerminal()))
				.subscription(SubscriptionAssembler.toDto(subscriber.getSubscription()))
				.secondsCalled(subscriber.getSecondsCalled())
				.dataUsed(subscriber.getDataUsed())
				.build();
	}

	public static List<SubscriberViewDto> toDtoList(List<Subscriber> subscriberList) {
		return subscriberList.stream().map(SubscriberAssembler::toDto).collect(Collectors.toList());
	}
}
