package de.inhorn.cybhorn.invoice;

import de.inhorn.cybhorn.model.Subscriber;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {

    private Subscriber subscriber;
    private double totalcosts;

    public Invoice(Subscriber subscriber) {
        this.subscriber = subscriber;
        calculateCosts();
    }

    private void calculateCosts() {
        // calculate the seconds to be payed
        int secondsToBePayed = subscriber.getSecondsCalled() - subscriber.getSubscription().getSecondsIncluded();

        // calculate the costs of these seconds per started minute
        this.totalcosts = Math.ceil(secondsToBePayed / 60) * (subscriber.getSubscription().getPricePerSecond()*60);
        if(totalcosts < 0) {
            totalcosts = 0;
        }
        // add basic fee for subscription
        totalcosts += subscriber.getSubscription().getBasicFee();
    }
}
