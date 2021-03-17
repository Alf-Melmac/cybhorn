package de.inhorn.cybhorn;


public class Subscriber {

    private String[] IMSI = new String[3];
    private Terminaltype terminalType;
    private Subscriptiontype subscriptionType;
    private int secondsCalled;
    private double dataUsed;  //in MB


    public Subscriber(String[] pIMSI, Terminaltype pTerminaltype){
        IMSI = pIMSI;
        terminalType = pTerminaltype;
    }

    public void setSecondsCalled(int set){
        secondsCalled = set;
    }

    public int getSecondsCalled(){
        return secondsCalled;
    }

    public void setDataUsed(double set){
        dataUsed = set;
    }

    public double getDataUsed(){
        return dataUsed;
    }

    public void setSubscriptionType(Subscriptiontype set){
        subscriptionType = set;
    }

    public void clearAll(){

    }


}
