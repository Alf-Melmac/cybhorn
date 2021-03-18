package de.inhorn.cybhorn.model.dtos;

import lombok.Builder;
import lombok.Value;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Builder
@Value
public class SubscriberPostDto {
	String mcc;
	String mnc;
	String msin;
	String firstName;
	String lastName;

	TerminalDto terminal;
	SubscriptionDto subscription;
	int secondsCalled;
	double dataUsed;
}
