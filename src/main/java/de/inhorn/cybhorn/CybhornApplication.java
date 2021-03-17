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

	public static void addSubscriber(String[] pIMSI, Terminaltype pTerminaltype, Subscriptiontype pSubscriptiontype){
		// TODO: Eingabemaske
		
		Subscriber s = new Subscriber(pIMSI, pTerminaltype, pSubscriptiontype);

		// TODO: Add to DB
	}

	public static void removeSubscriber(String[] pIMSI){
		// TODO remove subscriber from DB
	}

	public static void printSubscriber(String[] pIMSI){
		// TODO iterate over subs and print with relevant data
	}


}
