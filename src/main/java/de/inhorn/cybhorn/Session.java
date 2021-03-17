package de.inhorn.cybhorn;

import de.inhorn.cybhorn.model.Subscriber;

import lombok.*;

public class Session {

	enum services {DATA, CALL}


	public Session(int pDuration, services pService, Subscriber pSubscriber) {
		switch (pService) {
            case DATA:

                // calculate throughput
                int r = (int)Math.random()*pSubscriber.getTerminal().getSupportedRanTechnology().getRandom().length;
                double random = pSubscriber.getTerminal().getSupportedRanTechnology().getRandom()[r];

                // calculate dataUsed
                double dataUsed = (double)pSubscriber.getTerminal().getSupportedRanTechnology().getMbits() * 8 * random * pDuration;

                if(dataUsed +  pSubscriber.getDataUsed() > pSubscriber.getSubscription().getDataVolume()) throw new IllegalArgumentException("not enough data volume left");
                else pSubscriber.setDataUsed(pSubscriber.getDataUsed() + dataUsed);
				break;

            case CALL:

				pSubscriber.setSecondsCalled(pSubscriber.getSecondsCalled() + pDuration);
				break;

			default:
				System.out.println("invalid service");
				break;
		}
	}

}
