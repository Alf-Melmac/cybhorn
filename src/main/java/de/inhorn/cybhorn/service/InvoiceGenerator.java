package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.invoice.PDFCreator;
import de.inhorn.cybhorn.model.Invoice;
import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.repository.SubscriberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class InvoiceGenerator {
	private final SubscriberRepository subscriberRepository;

	public void createInvoices() {
		subscriberRepository.findAll().forEach(this::createInvoice);
	}

	public void createInvoice(@NonNull Subscriber subscriber) {
		PDFCreator.createPDF(new Invoice(subscriber));
		subscriber.reset();
	}
}
