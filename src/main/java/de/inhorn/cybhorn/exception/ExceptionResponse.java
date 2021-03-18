package de.inhorn.cybhorn.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alf
 * @since 18.03.2021
 */
@Getter
@Setter
@Builder
public class ExceptionResponse {
	private String errorMessage;
	private String requestedURI;
}
