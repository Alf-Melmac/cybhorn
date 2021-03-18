package de.inhorn.cybhorn.util;

import de.inhorn.cybhorn.model.enums.RanTechnology;
import lombok.experimental.UtilityClass;
import org.thymeleaf.util.StringUtils;

import java.util.function.Consumer;

/**
 * @author Alf
 * @since 18.03.2021
 */
@UtilityClass
public final class DtoUtils {

	private static boolean isPresent(int value) {
		return value != 0;
	}

	public static boolean isPresent(long value) {
		return value != 0;
	}

	private static boolean isPresent(double value) {
		return value != 0;
	}

	private static boolean isPresent(String value) {
		return value != null && !StringUtils.isEmptyOrWhitespace(value);
	}

	private static boolean isPresent(Object value) {
		return value != null;
	}

	public static void ifPresent(String value, Consumer<String> consumer) {
		if (isPresent(value)) {
			consumer.accept(value);
		}
	}

	public static void ifPresent(int value, Consumer<Integer> consumer) {
		if (isPresent(value)) {
			consumer.accept(value);
		}
	}

	public static void ifPresent(long value, Consumer<Long> consumer) {
		if (isPresent(value)) {
			consumer.accept(value);
		}
	}

	public static void ifPresent(RanTechnology ranTechnology, Consumer<RanTechnology> consumer) {
		if (isPresent(ranTechnology)) {
			consumer.accept(ranTechnology);
		}
	}

	public static void ifPresentDouble(double value, Consumer<Double> consumer) {
		if (isPresent(value)) {
			consumer.accept(value);
		}
	}
}
