package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.assembler.TerminalAssembler;
import de.inhorn.cybhorn.model.dtos.TerminalDto;
import de.inhorn.cybhorn.repository.TerminalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alf
 * @since 17.03.2021
 */
@RestController
@RequestMapping("/terminals")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TerminalController {
	private final TerminalRepository terminalRepository;

	@GetMapping("/list")
	public List<TerminalDto> getAllTerminals() {
		return terminalRepository.findAll().stream().map(TerminalAssembler::toDto).collect(Collectors.toList());
	}
}
