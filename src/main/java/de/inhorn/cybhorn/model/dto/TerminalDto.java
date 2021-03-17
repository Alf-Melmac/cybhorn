package de.inhorn.cybhorn.model.dto;

import de.inhorn.cybhorn.model.Terminal;
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

	Terminal.RanTechnology supportedRanTechnology;
}
