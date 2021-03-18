package de.inhorn.cybhorn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

/**
 * @author Alf
 * @since 18.03.2021
 */
@Configuration
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("^(/sessions.*|/subscribers.*|/subscriptions.*|/terminals.*)"))
				.build();
	}
}
