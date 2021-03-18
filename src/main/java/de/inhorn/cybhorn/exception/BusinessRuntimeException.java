package de.inhorn.cybhorn.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;

/**
 * @author Alf
 * @since 18.03.2021
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class BusinessRuntimeException extends RuntimeException {
	@Builder
	private BusinessRuntimeException(@NotEmpty String title, Throwable cause) {
		super(title, cause);
	}
}
