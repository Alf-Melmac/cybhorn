package de.inhorn.cybhorn.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {

	private final Subscriber subscriber;
	private double totalCosts;

	public Invoice(Subscriber subscriber) {
		this.subscriber = subscriber;
		calculateCosts();
	}

	private void calculateCosts() {
		// calculate the seconds to be payed
		final Subscription subscription = subscriber.getSubscription();

		// add basic fee for subscription
		totalCosts = subscription.getBasicFee();

		final int secondsToBePayed = subscriber.getSecondsCalled() - subscription.getSecondsIncluded();
		if (secondsToBePayed > 0) {
			// calculate the costs of these seconds per started minute
			totalCosts += Math.ceil(secondsToBePayed / 60d) * (subscription.getPricePerMinute());
		}
	}
}
