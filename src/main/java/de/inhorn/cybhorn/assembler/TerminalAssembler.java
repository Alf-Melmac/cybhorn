package de.inhorn.cybhorn.assembler;

import de.inhorn.cybhorn.model.Terminal;
import de.inhorn.cybhorn.model.dto.TerminalDto;
import org.springframework.stereotype.Component;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Component
public class TerminalAssembler {
	public static Terminal fromDto(TerminalDto terminalDto) {
		if (terminalDto == null) {
			return null;
		}

		return Terminal.builder()
				.name(terminalDto.getName())
				.ranTechnology(terminalDto.getSupportedRanTechnology())
				.build();
	}
}
