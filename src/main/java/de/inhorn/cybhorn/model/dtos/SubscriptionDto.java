package de.inhorn.cybhorn.model.dtos;

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
public class SubscriptionDto extends AbstractIdEntityDto {
	private final String name;
	private final int basicFee;
	private final int secondsIncluded;
	private final double pricePerMinute;
	private final double dataVolume;
}
