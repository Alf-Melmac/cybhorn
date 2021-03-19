package de.inhorn.cybhorn.controller;

import de.inhorn.cybhorn.assembler.SubscriberAssembler;
import de.inhorn.cybhorn.invoice.PDFCreator;
import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.dtos.SubscriberEditDto;
import de.inhorn.cybhorn.model.dtos.SubscriberPostDto;
import de.inhorn.cybhorn.model.dtos.SubscriberViewDto;
import de.inhorn.cybhorn.service.InvoiceGenerator;
import de.inhorn.cybhorn.service.SubscriberService;
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
@RequestMapping("/subscribers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubscriberController {
	private final SubscriberService subscriberService;
	private final InvoiceGenerator invoiceGenerator;

	@GetMapping("/list")
	public List<SubscriberViewDto> getAllSubscribers() {
		return SubscriberAssembler.toDtoList(subscriberService.findAll());
	}

	@PostMapping
	public SubscriberViewDto postSubscriber(@Valid @RequestBody SubscriberPostDto subscriber) {
		return SubscriberAssembler.toDto(subscriberService.createSubscriber(subscriber));
	}

	@PutMapping("/{id}")
	public SubscriberViewDto updateSubscriber(@PathVariable long id, @Valid @RequestBody SubscriberEditDto subscriber) {
		subscriber.setImsi(id);
		return SubscriberAssembler.toDto(subscriberService.updateSubscriber(subscriber));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubscriber(@PathVariable long id) {
		subscriberService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/invoices")
	public ResponseEntity<Void> getAllInvoices() {
		invoiceGenerator.createInvoices().forEach(PDFCreator::createPDF);
		subscriberService.findAll().forEach(Subscriber::reset);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
