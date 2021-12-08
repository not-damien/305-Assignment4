package model;

public class Truck extends AbstractVehicle {

    public Truck(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "truck.gif";
        myDeathIcon = "truck_dead.gif";
    }
}
