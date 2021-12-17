package model;

import java.util.Map;

public class Car extends AbstractVehicle {

    public Car(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "car.gif";
        myDeathIcon = "car_dead.gif";
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return false;
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return null;
    }

    @Override
    public void collide(Vehicle theOther) {

    }

    @Override
    public int getDeathTime() {
        return 0;
    }

    @Override
    public void poke() {

    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {

    }
}
