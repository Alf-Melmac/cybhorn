package de.inhorn.cybhorn.invoice;

import de.inhorn.cybhorn.model.Subscriber;

public class Invoice {

    private Subscriber subscriber;
    private double totalcosts;

    public Invoice(Subscriber subscriber) {
        this.subscriber = subscriber;
        calculateCosts();
    }

    private void calculateCosts() {
        int secondsToBePlayed = subscriber.getSecondsCalled() - subscriber.getSubscription().getSecondsIncluded();
        this.totalcosts = Math.ceil(secondsToBePlayed / 60) * (subscriber.getSubscription().getPricePerSecond()*60);
        if(totalcosts < 0) {
            totalcosts = 0;
        }
    }
}
