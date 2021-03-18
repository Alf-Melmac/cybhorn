package de.inhorn.cybhorn.model;

import de.inhorn.cybhorn.exception.BusinessRuntimeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Alf
 * @since 18.03.2021
 */
class SubscriberShould {
	static final int VALID_MCC = 123;
	static final int VALID_MNC = 12;
	static final long VALID_MSIN = 123456789;

	@Test
	void buildValidImsi() {
		final Subscriber sut = buildValidSubscriber();
		assertThat(sut.getImsi())
				.isEqualTo(Long.parseLong(Integer.toString(VALID_MCC) + VALID_MNC + VALID_MSIN));
	}

	@Test
	void throwExceptionOnInvalidMcc() {
		assertThatExceptionOfType(BusinessRuntimeException.class)
				.isThrownBy(() -> Subscriber.builder()
						.mcc(1)
						.mnc(VALID_MNC).msin(VALID_MSIN)
						.build())
				.withMessage("Invalid MCC");
	}

	@Test
	void throwExceptionOnInvalidMnc() {
		assertThatExceptionOfType(BusinessRuntimeException.class)
				.isThrownBy(() -> Subscriber.builder()
						.mcc(VALID_MCC)
						.mnc(1)
						.msin(VALID_MSIN)
						.build())
				.withMessage("Invalid MNC");
	}

	@Test
	void throwExceptionOnInvalidMsin() {
		assertThatExceptionOfType(BusinessRuntimeException.class)
				.isThrownBy(() -> Subscriber.builder()
						.mcc(VALID_MCC).mnc(VALID_MNC)
						.msin(1)
						.build())
				.withMessage("Invalid MSIN");
	}

	@Test
	void addDataVolume() {
		Subscriber sut = buildSubscriberWithDataVolume(1);

		final int usedData = 1;
		sut.useData(usedData);
		assertThat(sut.getDataUsed()).isEqualTo(usedData);
	}

	@Test
	void throwExceptionIfFirstDataUsageDepletesDataVolume() {
		Subscriber sut = buildSubscriberWithDataVolume(1);

		assertThatExceptionOfType(BusinessRuntimeException.class).isThrownBy(() -> sut.useData(2))
				.withMessage("Data volume depleted.");
	}

	@Test
	void throwExceptionIfSecondDataUsageDepletesDataVolume() {
		Subscriber sut = buildSubscriberWithDataVolume(1);

		sut.useData(1);
		assertThatExceptionOfType(BusinessRuntimeException.class).isThrownBy(() -> sut.useData(1))
				.withMessage("Data volume depleted.");
	}

	private Subscriber buildSubscriberWithDataVolume(double dataVolume) {
		Subscriber subscriber = buildValidSubscriber();
		subscriber.setSubscription(Subscription.builder().dataVolume(dataVolume).build());
		return subscriber;
	}

	private static Subscriber buildValidSubscriber() {
		return Subscriber.builder().mcc(VALID_MCC).mnc(VALID_MNC).msin(VALID_MSIN).build();
	}
}