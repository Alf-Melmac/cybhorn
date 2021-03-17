package de.inhorn.cybhorn.model.dto;

import lombok.Builder;
import lombok.Value;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Builder
@Value
public class SubscriberDto {
	String mcc;

	String mnc;

	String msin;

	TerminalDto terminal;
}
