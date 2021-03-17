package de.inhorn.cybhorn.invoice;

import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.repository.SubscriberRepository;

import java.util.List;

public class InvoiceGenerator {
    private SubscriberRepository subscriberRepository;
    private List<Invoice> invoices;

    public InvoiceGenerator() {
        List<Subscriber> subscribers = subscriberRepository.findAll();

        for(Subscriber subscriber: subscribers) {
            Invoice invoice = new Invoice(subscriber);
            invoices.add(invoice);
        }
    }
}
