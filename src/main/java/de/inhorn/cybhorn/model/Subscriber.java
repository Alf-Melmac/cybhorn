package de.inhorn.cybhorn.model;

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
	public Subscriber(int mcc, int mnc, long msin, Terminal terminal, Subscription subscription, int secondsCalled, double dataUsed) {
		final String mccString = Integer.toString(mcc);
		final String mncString = Integer.toString(mnc);
		final int mncLength = mncString.length();
		final String msinString = Long.toString(msin);
		final int msinLength = msinString.length();
		if (mccString.length() != 3) {
			throw new IllegalArgumentException("Ungueltige mcc");
		} else if (mncLength < 2 || mncLength > 3) {
			throw new IllegalArgumentException("Ungueltige mnc");
		} else if (msinLength < 9 || msinLength > 10) {
			throw new IllegalArgumentException("Ungueltige msin");
		}

		this.imsi = Long.parseLong(mccString + mncString + msinString);
		//TODO name

		this.terminal = terminal;
		this.subscription = subscription;
		this.secondsCalled = secondsCalled;
		this.dataUsed = dataUsed;
	}
}
