package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.exception.BusinessRuntimeException;
import de.inhorn.cybhorn.model.Terminal;
import de.inhorn.cybhorn.repository.SubscriberRepository;
import de.inhorn.cybhorn.repository.TerminalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

/**
 * @author Alf
 * @since 18.03.2021
 */
@ExtendWith(MockitoExtension.class)
class TerminalServiceShould {
	@Mock
	private SubscriberRepository subscriberRepository;

	@Mock
	private TerminalRepository terminalRepository;

	@InjectMocks
	private TerminalService sut;

	@Test
	void deleteTerminalIfNotUsed() {
		Terminal terminal = mock(Terminal.class);
		when(subscriberRepository.existsByTerminal(terminal)).thenReturn(false);

		sut.deleteTerminal(terminal);

		verify(terminalRepository, times(1)).delete(terminal);
	}

	@Test
	void throwExceptionIfTerminalToDeleteIsBeingUsed() {
		Terminal terminal = mock(Terminal.class);
		when(subscriberRepository.existsByTerminal(terminal)).thenReturn(true);

		assertThatExceptionOfType(BusinessRuntimeException.class).isThrownBy(() -> sut.deleteTerminal(terminal))
				.withMessage("Terminal is still in use by at least one subscriber.");
	}
}