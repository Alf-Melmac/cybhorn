package de.inhorn.cybhorn.model.dtos;

import de.inhorn.cybhorn.model.enums.ServiceType;
import lombok.Builder;
import lombok.Value;

/**
 * @author Alf
 * @since 18.03.2021
 */
@Builder
@Value
public class SessionDto {
	long imsi;
	ServiceType serviceType;
	int duration;
}
