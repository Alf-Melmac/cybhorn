package de.inhorn.cybhorn;

public class Session {
    
    enum services {DATA, CALL}
    private int duration; //in seconds
    private services service;
    private Subscriber subscriber;
}
