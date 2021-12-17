package model;

import java.util.Map;

public class Bicycle extends AbstractVehicle {
    public Bicycle(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "bicycle.gif";
        myDeathIcon = "bicycle_dead.gif";
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return false;//todo
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return null;//todo
    }

    @Override
    public void collide(Vehicle theOther) {
//todo
    }

    @Override
    public int getDeathTime() {
        return 0;//todo
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

    //TODO
}
