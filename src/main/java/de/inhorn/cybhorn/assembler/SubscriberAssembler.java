package de.inhorn.cybhorn.assembler;

import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.dto.SubscriberDto;
import org.springframework.stereotype.Component;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Component
public class SubscriberAssembler {
	public static Subscriber fromDto(SubscriberDto subscriberDto) {
		if (subscriberDto == null) {
			return null;
		}

		return Subscriber.builder()
				.mcc(Integer.parseInt(subscriberDto.getMcc()))
				.mnc(Integer.parseInt(subscriberDto.getMnc()))
				.msin(Integer.parseInt(subscriberDto.getMsin()))
				.terminal(TerminalAssembler.fromDto(subscriberDto.getTerminal()))
				.build();
	}
}
