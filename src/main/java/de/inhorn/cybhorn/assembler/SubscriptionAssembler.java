package de.inhorn.cybhorn.assembler;

import de.inhorn.cybhorn.model.Subscription;
import de.inhorn.cybhorn.model.dtos.SubscriptionDto;
import org.springframework.stereotype.Component;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Component
public class SubscriptionAssembler {
	public static Subscription fromDto(SubscriptionDto subscriptionDto) {
		if (subscriptionDto == null) {
			return null;
		}

		return Subscription.builder()
				.name(subscriptionDto.getName())
				.basicFee(subscriptionDto.getBasicFee())
				.secondsIncluded(subscriptionDto.getSecondsIncluded())
				.pricePerSecond(subscriptionDto.getPricePerSecond())
				.dataVolume(subscriptionDto.getDataVolume())
				.build();
	}

	public static SubscriptionDto toDto(Subscription subscription) {
		return SubscriptionDto.builder()
				.name(subscription.getName())
				.basicFee(subscription.getBasicFee())
				.secondsIncluded(subscription.getSecondsIncluded())
				.pricePerSecond(subscription.getPricePerSecond())
				.dataVolume(subscription.getDataVolume())
				.build();
	}
}
