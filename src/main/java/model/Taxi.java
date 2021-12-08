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

    @Override
    public int getDeathTime() {
        return 0;
    }

    @Override
    public void poke() {

    }
    //TODO
}
