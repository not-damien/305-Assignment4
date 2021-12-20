package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Truck.
 */
public class Truck extends AbstractVehicle {

    /**
     * Instantiates a new Truck.
     *
     * @param theX   the the x
     * @param theY   the the y
     * @param theDir the the dir
     */
    public Truck(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "truck.gif";
        myDeathIcon = "truck_dead.gif";

    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     * Trucks can drive on Streets and through lights and crosswalks
     *Truck dont stop for traffic lights
     * trucks will not pass through crosswalks with red lights
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        if(validTerrain.contains(theTerrain)){
            if(theTerrain == Terrain.CROSSWALK){
                return theLight != Light.RED;
            }else{
                return true;
            }
        }
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
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        if(!(validTerrain.contains(theNeighbors.get(getDirection())) &&
                validTerrain.contains((theNeighbors.get(getDirection().left()))) &&
                validTerrain.contains((theNeighbors.get(getDirection().right()))))){
            return getDirection().reverse();
        }
        Direction temp = Direction.random();
        while(temp == getDirection().reverse() || !validTerrain.contains(theNeighbors.get(temp))){
            temp = Direction.random();
        }
        return temp;
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther) {
        //survives
    }



}
