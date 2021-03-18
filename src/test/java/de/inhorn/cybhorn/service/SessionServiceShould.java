package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.exception.BusinessRuntimeException;
import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.dtos.SessionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static de.inhorn.cybhorn.model.enums.ServiceType.BROWSING;
import static de.inhorn.cybhorn.model.enums.ServiceType.CALL;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

/**
 * @author Alf
 * @since 18.03.2021
 */
@ExtendWith(MockitoExtension.class)
class SessionServiceShould {
	@Mock
	private SubscriberService subscriberService;

	@Mock
	private TerminalService terminalService;

	@InjectMocks
	private SessionService sut;

	@Test
	void addSecondsCalled() {
		final int imsi = 123;
		final int duration = 1;
		final SessionDto session = SessionDto.builder()
				.imsi(imsi)
				.serviceType(CALL)
				.duration(duration)
				.build();

		final Subscriber subscriber = mock(Subscriber.class);
		when(subscriberService.findByImsi(imsi)).thenReturn(subscriber);

		sut.bookSession(session);

		verify(subscriber, times(1)).addSecondsCalled(duration);
	}

	@Test
	void notAddSecondsCalledIfDataWasUsed() {
		final int imsi = 123;
		final int duration = 1;
		final SessionDto session = SessionDto.builder()
				.imsi(imsi)
				.serviceType(BROWSING)
				.duration(duration)
				.build();

		final Subscriber subscriber = mock(Subscriber.class);
		when(subscriberService.findByImsi(imsi)).thenReturn(subscriber);
		when(subscriber.getTerminal()).thenReturn(null);
		when(terminalService.calculateMaxThroughput(null)).thenReturn(Double.MAX_VALUE);

		sut.bookSession(session);

		verify(subscriber, times(0)).addSecondsCalled(duration);
	}

	@Test
	void useData() {
		final int imsi = 123;
		final int duration = 1;
		final SessionDto session = SessionDto.builder()
				.imsi(imsi)
				.serviceType(BROWSING)
				.duration(duration)
				.build();

		final Subscriber subscriber = mock(Subscriber.class);
		when(subscriberService.findByImsi(imsi)).thenReturn(subscriber);
		when(subscriber.getTerminal()).thenReturn(null);

		final double maxThroughput = Double.MAX_VALUE;
		when(terminalService.calculateMaxThroughput(null)).thenReturn(maxThroughput);

		sut.bookSession(session);

		verify(subscriber, times(1)).useData(maxThroughput * duration);
	}

	@Test
	void throwExceptionIfConnectionTooSlow() {
		final int imsi = 123;
		final int duration = 1;
		final SessionDto session = SessionDto.builder()
				.imsi(imsi)
				.serviceType(BROWSING)
				.duration(duration)
				.build();

		final Subscriber subscriber = mock(Subscriber.class);
		when(subscriberService.findByImsi(imsi)).thenReturn(subscriber);
		when(subscriber.getTerminal()).thenReturn(null);

		when(terminalService.calculateMaxThroughput(null)).thenReturn(0d);

		assertThatExceptionOfType(BusinessRuntimeException.class).isThrownBy(() -> sut.bookSession(session))
				.withMessage("Connection too slow, go up a mountain.");
	}
}