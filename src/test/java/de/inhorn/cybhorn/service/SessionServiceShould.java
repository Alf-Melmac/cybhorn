package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.exception.BusinessRuntimeException;
import de.inhorn.cybhorn.model.dtos.SessionDto;
import de.inhorn.cybhorn.model.enums.ServiceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Alf
 * @since 18.03.2021
 */
@ExtendWith(MockitoExtension.class)
class SessionServiceShould {
	@InjectMocks
	private SessionService sut;

	@Test
	void bookSession() {
		assertThatExceptionOfType(BusinessRuntimeException.class).isThrownBy(() -> sut.bookSession(SessionDto.builder().build()))
				.withMessage("");
	}

	private SessionDto buildSession() {
		return SessionDto.builder()
				.imsi(123)
				.serviceType(ServiceType.CALL)
				.duration(123)
				.build();
	}
}