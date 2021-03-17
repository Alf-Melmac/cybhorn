package de.inhorn.cybhorn;


public class Subscriber {

    private String[] IMSI = new String[3];
    private Terminaltype terminalType;
    private Subscriptiontype subscriptionType;
    private int secondsCalled = 0; 
    private double dataUsed = 0;  //in MB


    public Subscriber(String[] pIMSI, Terminaltype pTerminaltype, Subscriptiontype pSubscriptiontype){

        // 3 + 2 + [0;10] ODER 3 + 3 + [0;9]
        if(!(pIMSI[0].length()==3 && ( (pIMSI[1].length() == 2 && pIMSI[2].length()<11) || (pIMSI[1].length()==3 && pIMSI[2].length()<10) ))){
            throw new IllegalArgumentException("ungueltige IMSI");
        } 


        IMSI = pIMSI;
        terminalType = pTerminaltype;
        subscriptionType = pSubscriptiontype;
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

    public void reset(){

    }


}
