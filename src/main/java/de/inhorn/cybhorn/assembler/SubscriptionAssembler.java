package de.inhorn.cybhorn.assembler;

import de.inhorn.cybhorn.model.Subscription;
import de.inhorn.cybhorn.model.dtos.SubscriptionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
				.id(subscriptionDto.getId())
				.name(subscriptionDto.getName())
				.basicFee(subscriptionDto.getBasicFee())
				.secondsIncluded(subscriptionDto.getSecondsIncluded())
				.pricePerSecond(subscriptionDto.getPricePerSecond())
				.dataVolume(subscriptionDto.getDataVolume())
				.build();
	}

	public static SubscriptionDto toDto(Subscription subscription) {
		if (subscription == null) {
			return null;
		}

		return SubscriptionDto.builder()
				.id(subscription.getId())
				.name(subscription.getName())
				.basicFee(subscription.getBasicFee())
				.secondsIncluded(subscription.getSecondsIncluded())
				.pricePerSecond(subscription.getPricePerSecond())
				.dataVolume(subscription.getDataVolume())
				.build();
	}

	public static List<SubscriptionDto> toDtoList(List<Subscription> subscriptionList) {
		return subscriptionList.stream().map(SubscriptionAssembler::toDto).collect(Collectors.toList());
	}
}
