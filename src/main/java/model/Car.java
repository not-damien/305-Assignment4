package model;

public class Car extends AbstractVehicle {

    public Car(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "car.gif";
        myDeathIcon = "car_dead.gif";
    }
}
