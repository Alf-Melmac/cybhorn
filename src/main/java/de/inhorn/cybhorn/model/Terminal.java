package de.inhorn.cybhorn.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "terminal", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Terminal extends AbstractIdEntity {
	@Column(name = "name")
	@NotBlank
	private String name;

	@Column(name = "supported_ran")
	@Enumerated(EnumType.STRING)
	@NotBlank
	private Terminal.RanTechnology supportedRanTechnology;

	@Builder
	public Terminal(@NotBlank String name,
					@NonNull RanTechnology ranTechnology) {
		this.name = name;
		this.supportedRanTechnology = ranTechnology;
	}

	public enum RanTechnology {
		TWO_G,
		THREE_G,
		FOUR_G;
	}
}
