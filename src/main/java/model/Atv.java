package model;

import java.util.Map;

/**
 * Movement behavior:
 * o Trucks travel only on streets and through lights and crosswalks.
 * o Trucks randomly select to go straight, turn left, or turn right.  As a last resort, if none of these three
 * directions is legal (all not streets, lights, or crosswalks), the truck turns around.
 * o Trucks drive through all traffic lights without stopping!
 * o Trucks  stop  for  red  crosswalk  lights  but  drive  through  yellow  or  green  crosswalk  lights  without
 * stopping.
 * • Collision behavior: A truck survives a collision with anything, living or dead.
 */
public class Atv extends AbstractVehicle{
    public Atv(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "atv.gif";
        myDeathIcon = "atv_dead.gif";
        myDeathTime = 25;
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
        if (theTerrain == Terrain.WALL) {
            return false;
        } else {
            return true;
        }

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
     *
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther) {
        Class theirClass = theOther.getClass();
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
