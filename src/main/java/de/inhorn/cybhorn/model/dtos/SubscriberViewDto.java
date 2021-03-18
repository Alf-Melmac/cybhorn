package de.inhorn.cybhorn.model.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Builder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class SubscriberViewDto {
	private final String imsi;
	private final String name;
	private final String firstName;
	private final String lastName;
	private final TerminalDto terminal;
	private final SubscriptionDto subscription;
	private final int secondsCalled;
	private final double dataUsed;
}
