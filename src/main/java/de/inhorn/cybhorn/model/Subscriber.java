package de.inhorn.cybhorn.model;

import de.inhorn.cybhorn.Subscriptiontype;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "terminal_id")
	private Terminal terminal;


	//	private Subscriptiontype subscriptionType;
	private int secondsCalled = 0;
	private double dataUsed = 0;  //in MB

	@Builder
	public Subscriber(int mcc, int mnc, long msin, Terminal terminal, Subscriptiontype subscriptionType) {

		final int mccLength = Integer.toString(mcc).length();
		final int mncLength = Integer.toString(mnc).length();
		final int msinLength = Long.toString(msin).length();
		if (mccLength != 3) {
			throw new IllegalArgumentException("Ungueltige mcc");
		} else if (mncLength < 2 || mncLength > 3) {
			throw new IllegalArgumentException("Ungueltige mnc");
		} else if (msinLength < 9 || msinLength > 10) {
			throw new IllegalArgumentException("Ungueltige msin");
		}


		this.terminal = terminal;
//		this.subscriptionType = subscriptionType;
	}
}
