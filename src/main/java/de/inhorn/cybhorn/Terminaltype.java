package de.inhorn.cybhorn;

public class Terminaltype {

    enum technologies {TWO_G, THREE_G, FOUR_G};
    public technologies supported = technologies.TWO_G;
    public String name;

    public Terminaltype(){

    }

    public technologies getSupported() {
        return supported;
    }


}
