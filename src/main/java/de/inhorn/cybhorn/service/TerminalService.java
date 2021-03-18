package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.assembler.TerminalAssembler;
import de.inhorn.cybhorn.exception.BusinessRuntimeException;
import de.inhorn.cybhorn.exception.ResourceNotFoundException;
import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.Terminal;
import de.inhorn.cybhorn.model.dtos.TerminalDto;
import de.inhorn.cybhorn.model.enums.RanTechnology;
import de.inhorn.cybhorn.repository.SubscriberRepository;
import de.inhorn.cybhorn.repository.TerminalRepository;
import de.inhorn.cybhorn.util.DtoUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TerminalService {
	private final TerminalRepository terminalRepository;
	private final SubscriberRepository subscriberRepository;

	/**
	 * Creates a new terminal using the {@link TerminalAssembler#fromDto(TerminalDto)} method
	 *
	 * @return the new terminal
	 */
	public Terminal createTerminal(TerminalDto dto) {
		return terminalRepository.save(TerminalAssembler.fromDto(dto));
	}

	public Terminal findById(long id) {
		return terminalRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * @return all terminals oder by name
	 */
	public List<Terminal> findAllOrdered() {
		return terminalRepository.findAll(Sort.by("name"));
	}

	/**
	 * Updates the {@link Terminal} with the given values from the dto, if present. Found by id of dto
	 *
	 * @param dto with id and values to update
	 * @return updated terminal
	 */
	public Terminal updateTerminal(@NonNull TerminalDto dto) {
		Terminal terminal = findById(dto.getId());

		DtoUtils.ifPresent(dto.getName(), terminal::setName);
		DtoUtils.ifPresent(dto.getSupportedRanTechnology(), terminal::setSupportedRanTechnology);

		return terminal;
	}

	/**
	 * Deletes the given terminal if it isn't used by a {@link Subscriber}
	 *
	 * @param terminal to delete
	 */
	public void deleteTerminal(Terminal terminal) {
		if (!subscriberRepository.existsByTerminal(terminal)) {
			terminalRepository.delete(terminal);
		} else {
			throw BusinessRuntimeException.builder().title("Terminal is still in use by at least one subscribers").build();
		}
	}

	/**
	 * Calculates the achievable throughput for the given {@link Terminal}
	 *
	 * @param terminal to calculate data rate for
	 * @return the data rate with the random signal quality
	 */
	public double calculateMaxThroughput(Terminal terminal) {
		final RanTechnology supportedRanTechnology = terminal.getSupportedRanTechnology();

		final double[] signalQualities = supportedRanTechnology.getSignalQualities();
		final int length = signalQualities.length;
		if (length == 0) {
			return 0;
		}
		final double throughput = signalQualities[new Random().nextInt(length)];

		// calculate throughput
		return supportedRanTechnology.getMbits() * 8 * throughput;
	}
}
