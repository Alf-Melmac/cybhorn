package de.inhorn.cybhorn.assembler;

import de.inhorn.cybhorn.model.Terminal;
import de.inhorn.cybhorn.model.dtos.TerminalDto;
import de.inhorn.cybhorn.model.dtos.TerminalViewDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
				.id(terminalDto.getId())
				.name(terminalDto.getName())
				.ranTechnology(terminalDto.getSupportedRanTechnology())
				.build();
	}

	public static TerminalViewDto toDto(Terminal terminal) {
		if (terminal == null) {
			return null;
		}

		return TerminalViewDto.builder()
				.id(terminal.getId())
				.name(terminal.getName())
				.supportedRanTechnology(terminal.getSupportedRanTechnology())
				.supportedRanTechnologyName(terminal.getSupportedRanTechnology().getName())
				.build();
	}

	public static List<TerminalViewDto> toDtoList(List<Terminal> slotList) {
		return slotList.stream().map(TerminalAssembler::toDto).collect(Collectors.toList());
	}
}
