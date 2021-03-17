package de.inhorn.cybhorn.model.dtos;

import de.inhorn.cybhorn.model.enums.RanTechnology;
import lombok.Builder;
import lombok.Value;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Builder
@Value
public class TerminalDto {
	String name;

	RanTechnology supportedRanTechnology;
}
