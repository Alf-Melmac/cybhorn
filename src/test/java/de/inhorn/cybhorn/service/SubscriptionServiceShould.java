package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.exception.BusinessRuntimeException;
import de.inhorn.cybhorn.model.Subscription;
import de.inhorn.cybhorn.repository.SubscriberRepository;
import de.inhorn.cybhorn.repository.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

/**
 * @author Alf
 * @since 18.03.2021
 */
@ExtendWith(MockitoExtension.class)
class SubscriptionServiceShould {
	@Mock
	private SubscriberRepository subscriberRepository;

	@Mock
	private SubscriptionRepository subscriptionRepository;

	@InjectMocks
	private SubscriptionService sut;

	@Test
	void deleteSubscriptionIfNotUsed() {
		Subscription subscription = mock(Subscription.class);
		when(subscriberRepository.existsBySubscription(subscription)).thenReturn(false);

		sut.deleteSubscription(subscription);

		verify(subscriptionRepository, times(1)).delete(subscription);
	}

	@Test
	void throwExceptionIfSubscriptionToDeleteIsBeingUsed() {
		Subscription subscription = mock(Subscription.class);
		when(subscriberRepository.existsBySubscription(subscription)).thenReturn(true);

		assertThatExceptionOfType(BusinessRuntimeException.class).isThrownBy(() -> sut.deleteSubscription(subscription))
				.withMessage("Subscription is still in use by at least one subscriber.");
	}
}