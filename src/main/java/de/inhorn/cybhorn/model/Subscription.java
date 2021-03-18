package de.inhorn.cybhorn.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "subscription", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Subscription extends AbstractIdEntity {
	@Column(name = "name")
	private String name;

	@Column(name = "basic_fee")
	private int basicFee;

	@Column(name = "seconds_included")
	private int secondsIncluded;

	@Column(name = "price_per_second")
	private double pricePerSecond;

	@Column(name = "data_volume")
	private double dataVolume; // in MB

	@Builder
	public Subscription(long id, String name, int basicFee, int secondsIncluded, double pricePerSecond, double dataVolume) {
		this.id = id;
		this.name = name;
		this.basicFee = basicFee;
		this.secondsIncluded = secondsIncluded;
		this.pricePerSecond = pricePerSecond;
		this.dataVolume = dataVolume;
	}

}