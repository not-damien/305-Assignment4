package model;

public class Bicycle extends AbstractVehicle {
    public Bicycle(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "bicycle.gif";
        myDeathIcon = "bicycle_dead.gif";
    }

    //TODO
}
