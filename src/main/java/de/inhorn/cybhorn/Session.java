package de.inhorn.cybhorn;

import de.inhorn.cybhorn.model.Subscriber;

public class Session {

	enum services {DATA, CALL}

	private int duration; //in seconds
	private services service;
	private Subscriber subscriber;

	public Session(int pDuration, services pService, Subscriber pSubscriber) {
		switch (service) {
            case DATA:

                int r = (int)Math.random()*pSubscriber.getTerminal().getSupportedRanTechnology().getRandom().length;
                double random = pSubscriber.getTerminal().getSupportedRanTechnology().getRandom()[r];
                double dataUsed = (double)pSubscriber.getTerminal().getSupportedRanTechnology().getMbit() * random * duration;

                pSubscriber.setDataUsed(pSubscriber.getDaraUsed() - dataUsed);
				break;

			case CALL:
				pSubscriber.setSecondsCalled(pSubscriber.getSecondsCalled - duration);
				break;

			default:
				System.out.println("invalid service");
				break;
		}
	}

}
