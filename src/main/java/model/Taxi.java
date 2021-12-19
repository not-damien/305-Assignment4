package model;

import java.util.Map;

public class Taxi extends AbstractVehicle {
    public Taxi(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "taxi.gif";
        myDeathIcon = "taxi_dead.gif";
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

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        super.reset();
    }


    //TODO
}
