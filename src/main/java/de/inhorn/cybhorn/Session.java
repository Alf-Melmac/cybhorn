package de.inhorn.cybhorn;

public class Session {
    
    enum services {DATA, CALL}
    private int duration; //in seconds
    private services service;
    private Subscriber subscriber;

    public Session (int pDuration, services pService, Subscriber pSubscriber){
        switch (service) {
            case DATA:
                System.out.println(subscriber.getTerminaltype().getSupported(););
            
                // zufallsgenerator dingens für Durchflussrate



                break;
                    
            case CALL:
                System.out.println("Fridays are better.");
                break;
                          
            default:
                System.out.println("invalid service");
                break;
        }
    }

}
