package de.inhorn.cybhorn.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceType {
	CALL("Voice call", 0),
	BROWSING("Browsing and social networking", 2),
	DOWNLOAD("App download", 10),
	VIDEO("Adaptive HD video", 100);

	private final String name;
	private final double requiredDataRate;
}