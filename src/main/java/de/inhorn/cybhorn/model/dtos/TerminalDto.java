package de.inhorn.cybhorn.model.dtos;

import de.inhorn.cybhorn.model.enums.RanTechnology;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Alf
 * @since 17.03.2021
 */
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@SuperBuilder
public class TerminalDto extends AbstractIdEntityDto {
	private final String name;

	private final RanTechnology supportedRanTechnology;
}
