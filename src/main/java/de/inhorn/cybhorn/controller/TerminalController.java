package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.assembler.TerminalAssembler;
import de.inhorn.cybhorn.model.dtos.TerminalDto;
import de.inhorn.cybhorn.model.dtos.TerminalViewDto;
import de.inhorn.cybhorn.repository.TerminalRepository;
import de.inhorn.cybhorn.service.TerminalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Alf
 * @since 17.03.2021
 */
@RestController
@RequestMapping("/terminals")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TerminalController {
	private final TerminalService terminalService;
	private final TerminalRepository terminalRepository;

	@GetMapping("/list")
	public List<TerminalViewDto> getAllTerminals() {
		return TerminalAssembler.toDtoList(terminalRepository.findAll());
	}

	@PostMapping
	public TerminalDto postTerminal(@Valid @RequestBody TerminalDto terminal) {
		return TerminalAssembler.toDto(terminalService.createTerminal(terminal));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTerminal(@PathVariable long id) {
		terminalRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
