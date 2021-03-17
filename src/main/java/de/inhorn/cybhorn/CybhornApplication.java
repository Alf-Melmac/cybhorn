package de.inhorn.cybhorn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CybhornApplication {

	public static void main(String[] args) {
		SpringApplication.run(CybhornApplication.class, args);

		// Terminaltype t = new Terminaltype();
		// String[] str = {"123", "11", "12233456"};
		// Subscriber sub = new Subscriber(str, t, new Subscriptiontype());
		// Session s = new Session (29384, Session.services.DATA, sub);
	}
}
