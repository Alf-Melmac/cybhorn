package de.inhorn.cybhorn;

import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.enums.ServiceType;

public class Session {


	public Session(int pDuration, ServiceType pService, Subscriber pSubscriber) {
		
		if(pService == ServiceType.CALL){

			pSubscriber.setSecondsCalled(pSubscriber.getSecondsCalled() + pDuration);
			return;
		}
		
		double maxThroughput = this.calculateMaxThroughput(pDuration, pService, pSubscriber);
		if(maxThroughput >= pService.getRequired()){
			double dataUsed = maxThroughput * pDuration;

			if(dataUsed +  pSubscriber.getDataUsed() <= pSubscriber.getSubscription().getDataVolume()) {
				
				pSubscriber.setDataUsed(pSubscriber.getDataUsed() + dataUsed);
			} else {
				throw new IllegalArgumentException("not enough data volume left");
			}

		}else{

			throw new IllegalArgumentException("Connection too slow, stand on a mountain.");
		}

	}

	public double calculateMaxThroughput(int pDuration, ServiceType pService, Subscriber pSubscriber){
		// calculate throughput
		int r = (int)Math.random()*pSubscriber.getTerminal().getSupportedRanTechnology().getRandom().length;
		double random = pSubscriber.getTerminal().getSupportedRanTechnology().getRandom()[r];

		// calculate throughput
		double throughput = pSubscriber.getTerminal().getSupportedRanTechnology().getMbits() * 8 * random;

		return throughput;
				
	}

}
