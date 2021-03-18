package de.inhorn.cybhorn.invoice;

import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class InvoiceGenerator {
    private final SubscriberRepository subscriberRepository;

    public List<Invoice> createInvoices() {
        List<Invoice> output = new ArrayList<>();
        List<Subscriber> subscribers = subscriberRepository.findAll();

        for(Subscriber subscriber: subscribers) {
            Invoice invoice = new Invoice(subscriber);
            output.add(invoice);
        }
        return output;
    }
}
