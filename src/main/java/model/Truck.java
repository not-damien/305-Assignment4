package model;

import java.util.Map;

public class Truck extends AbstractVehicle {

    public Truck(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "truck.gif";
        myDeathIcon = "truck_dead.gif";

    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return false;
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return null;
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther) {

    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {

    }


}
