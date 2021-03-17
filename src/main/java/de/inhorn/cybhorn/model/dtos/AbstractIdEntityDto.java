package de.inhorn.cybhorn.model.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author Alf
 * @since 17.03.2021
 */
@MappedSuperclass
@Data
@RequiredArgsConstructor
@SuperBuilder
public abstract class AbstractIdEntityDto {
	@Id
	@NotNull
	protected long id;
}