package model;

import java.util.Map;

/**
 * ATVs have a starting heath of 25
 * Class for vehicles of type ATV
 */
public class Atv extends AbstractVehicle{

    /**
     * Instantiates a new Atv.
     *
     * @param theX   the the x
     * @param theY   the the y
     * @param theDir the the dir
     */
    public Atv(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "atv.gif";
        myDeathIcon = "atv_dead.gif";
        myDeathTime = 25;
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *ATVS Ignore lights and drive on all terrain expect walls
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain != Terrain.WALL;

    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     * ATVs will only reverse when forward left and right are not an option
     * ATVs will not drive on walls
     * ATVS will randomly go forward left or right
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction temp = Direction.random();
        if(theNeighbors.get(getDirection()) == Terrain.WALL &&
                theNeighbors.get(getDirection().left()) == Terrain.WALL &&
                theNeighbors.get(getDirection().right()) == Terrain.WALL) {
            return getDirection().reverse();
        }else {
            while(temp == getDirection().reverse() || theNeighbors.get(temp) == Terrain.WALL ){
                temp = Direction.random();
            }
            return temp;
        }
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *Atvs die when colliding with trucks, cars or taxis
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther) {
        Class<? extends Vehicle> theirClass = theOther.getClass();
        if(theirClass == Truck.class || theirClass == Car.class || theirClass == Taxi.class ){
            isAlive = false;

        }
    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        super.reset();
        myDeathTime = 25;


    }

}
