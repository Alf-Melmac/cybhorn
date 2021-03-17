package de.inhorn.cybhorn.model.enums;
import lombok.*;
/**
 * @author Alf
 * @since 17.03.2021
 */

 @Getter
 @AllArgsConstructor
public enum RanTechnology {
	TWO_G(0, new double[0]),
    THREE_G(20, new double[]{0, 0.1, 0.25, 0.5}),
    FOUR_G(300, new double[]{0, 0.1, 0.25, 0.5});

	private final double mbits;
	private final double[] random;
}
