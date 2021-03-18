package de.inhorn.cybhorn.model;

import de.inhorn.cybhorn.exception.BusinessRuntimeException;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "subscriber", uniqueConstraints = {@UniqueConstraint(columnNames = {"imsi"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Subscriber {
	@Column(name = "imsi")
	@Id
	private long imsi;

	@JoinColumn(name = "first_name")
	private String firstName;

	@JoinColumn(name = "last_name")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "terminal_id")
	private Terminal terminal;

	@ManyToOne
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	@Column(name = "seconds_called")
	private int secondsCalled = 0;

	@Column(name = "data_used")
	private double dataUsed = 0;  //in MB

	@Builder
	public Subscriber(int mcc, int mnc, long msin, String firstName, String lastName, Terminal terminal, Subscription subscription, int secondsCalled, double dataUsed) {
		final String mccString = Integer.toString(mcc);
		final String mncString = Integer.toString(mnc);
		final int mncLength = mncString.length();
		final String msinString = Long.toString(msin);
		final int msinLength = msinString.length();
		if (mccString.length() != 3) {
			throw BusinessRuntimeException.builder().title("Invalid MCC").build();
		} else if (mncLength < 2 || mncLength > 3) {
			throw BusinessRuntimeException.builder().title("Invalid MNC").build();
		} else if (msinLength < 9 || msinLength > 10) {
			throw BusinessRuntimeException.builder().title("Invalid MSIN").build();
		}

		this.imsi = Long.parseLong(mccString + mncString + msinString);
		this.firstName = firstName;
		this.lastName = lastName;

		this.terminal = terminal;
		this.subscription = subscription;
		this.secondsCalled = secondsCalled;
		this.dataUsed = dataUsed;
	}

	/**
	 * Adds the given amount to the seconds called
	 *
	 * @param seconds to add
	 */
	public void addSecondsCalled(int seconds) {
		secondsCalled += seconds;
	}

	/**
	 * Adds the given amount to the used data
	 *
	 * @param data to add
	 * @throws BusinessRuntimeException if data volume is exceeded
	 */
	public void useData(double data) {
		final double dataUsage = dataUsed + data;
		if (dataUsage > subscription.getDataVolume()) {
			throw BusinessRuntimeException.builder().title("Data volume depleted.").build();
		}
		dataUsed = dataUsage;
	}

	public void reset() {
		secondsCalled = 0;
		dataUsed = 0;
	}
}
