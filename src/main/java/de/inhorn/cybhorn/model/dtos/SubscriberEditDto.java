package de.inhorn.cybhorn.model.dtos;

import lombok.Data;

/**
 * @author Alf
 * @since 18.03.2021
 */
@Data
public class SubscriberEditDto {
	private long imsi;
	private final String firstName;
	private final String lastName;

	private final long terminalId;
	private final long subscriptionId;
}
