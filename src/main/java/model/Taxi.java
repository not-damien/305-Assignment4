package model;

public class Taxi extends AbstractVehicle {
    public Taxi(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "taxi.gif";
        myDeathIcon = "taxi_dead.gif";
    }
    //TODO
}
