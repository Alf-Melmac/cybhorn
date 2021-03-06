package de.inhorn.cybhorn.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles exceptions thrown in all layers.
 * <p>
 * This translated exceptions to human readable and valuable error messages for the caller.
 *
 * @author Alf
 * @since 18.03.2021
 */
@ControllerAdvice
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {ResourceNotFoundException.class, BusinessRuntimeException.class})
	protected ResponseEntity<?> handleConflict(RuntimeException ex, HttpServletRequest request) {
		return new ResponseEntity<>(
				ExceptionResponse.builder()
						.errorMessage(determineErrorMessage(ex))
						.requestedURI(request.getRequestURI())
						.build(),
				determineHttpStatus(ex));
	}

	/**
	 * Uses the Annotation from the Exception if available
	 *
	 * @param e exception to check
	 * @return annotated reason or message of exception
	 */
	private String determineErrorMessage(Exception e) {
		ResponseStatus responseStatusAnnotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
		if (responseStatusAnnotation != null) {
			String reason = responseStatusAnnotation.reason();
			if (!StringUtils.isEmpty(reason)) {
				return responseStatusAnnotation.reason();
			}
		}
		return e.getMessage();
	}

	/**
	 * Uses the Annotation from the Exception if available
	 *
	 * @param e exception to check
	 * @return annotated Status or HttpStatus.INTERNAL_SERVER_ERROR
	 */
	private HttpStatus determineHttpStatus(Exception e) {
		ResponseStatus responseStatusAnnotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
		if (responseStatusAnnotation != null) {
			return responseStatusAnnotation.value();
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
