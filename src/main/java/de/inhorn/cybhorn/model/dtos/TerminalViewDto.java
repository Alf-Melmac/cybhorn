package de.inhorn.cybhorn.model.dtos;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * @author Alf
 * @since 17.03.2021
 */
@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class TerminalViewDto extends TerminalDto {
	String supportedRanTechnologyName;
}
