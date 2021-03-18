package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.assembler.TerminalAssembler;
import de.inhorn.cybhorn.model.Terminal;
import de.inhorn.cybhorn.model.dtos.TerminalDto;
import de.inhorn.cybhorn.repository.TerminalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TerminalService {
	private final TerminalRepository terminalRepository;

	public Terminal createTerminal(TerminalDto dto) {
		return terminalRepository.save(TerminalAssembler.fromDto(dto));
	}

	public List<Terminal> findAllOrdered() {
		return terminalRepository.findAll(Sort.by("name"));
	}
}
