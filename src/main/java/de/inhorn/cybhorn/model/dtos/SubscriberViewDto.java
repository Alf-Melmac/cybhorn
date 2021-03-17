package de.inhorn.cybhorn.model.dtos;

import lombok.Builder;
import lombok.Value;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Builder
@Value
public class SubscriberViewDto {
	String imsi;

	TerminalDto terminal;
}
