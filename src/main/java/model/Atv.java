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
        if(theTerrain == Terrain.CROSSWALK && theLight == Light.RED){
            return false;
        }else{
            return theTerrain == Terrain.STREET || theTerrain == Terrain.CROSSWALK;
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



    //TODO
}
