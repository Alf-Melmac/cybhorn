package de.inhorn.cybhorn.model;

import de.inhorn.cybhorn.model.enums.RanTechnology;
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
	private RanTechnology supportedRanTechnology;

	@Builder
	public Terminal(@NotBlank String name,
					@NonNull RanTechnology ranTechnology) {
		this.name = name;
		this.supportedRanTechnology = ranTechnology;
	}


}
