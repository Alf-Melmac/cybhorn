package de.inhorn.cybhorn.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Alf
 * @since 17.03.2021
 */
@MappedSuperclass
@Getter
public abstract class AbstractIdEntity {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	protected long id;
}
