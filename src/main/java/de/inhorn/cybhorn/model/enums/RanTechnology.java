package de.inhorn.cybhorn.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Alf
 * @since 17.03.2021
 */

@Getter
@AllArgsConstructor
public enum RanTechnology {
	TWO_G("2G", 0, new double[0]),
	THREE_G("3G", 20, new double[]{0, 0.1, 0.25, 0.5}),
	FOUR_G("4G", 300, new double[]{0, 0.1, 0.25, 0.5});

	private final String name;
	private final double mbits;
	private final double[] signalQualities;
}
