package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Car.
 */
public class Car extends AbstractVehicle {

    /**
     * Instantiates a new Car.
     *
     * @param theX   the the x
     * @param theY   the the y
     * @param theDir the the dir
     */
    public Car(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "car.gif";
        myDeathIcon = "car_dead.gif";
        myDeathTime = 15;
    }

    /**
     * cars only drive on streets and through lights and crosswalks
     * cars stop at red lights
     * cars only go through green lights
     *
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return true is the car caan pass
      */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight){
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);

        if(validTerrain.contains(theTerrain)){
            if(theTerrain == Terrain.LIGHT){
                return theLight != Light.RED;
            }
            if(theTerrain == Terrain.CROSSWALK){
                return theLight == Light.GREEN;
            }else{
                return true;
            }
        }else {
            return false;
        }
    }

    /**
     * cars prefer forward > left > right > reverse
     * @param theNeighbors The map of neighboring terrain.
     * @return the chosen direction
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        //car will go straight then left then right
        if(validTerrain.contains(theNeighbors.get(getDirection()))){
            return getDirection(); //go Straight
        }
        if(validTerrain.contains(theNeighbors.get(getDirection().left()))){
            return getDirection().left();
        }
        if(validTerrain.contains((theNeighbors.get(getDirection().right())))){
            return getDirection().right();
        }
        return getDirection().reverse();
    }

    /**
     * cars will only die if hit by a truck
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther) {
        if (theOther.getClass().getSimpleName().equalsIgnoreCase( "truck")){
            isAlive = false;
        }

    }
    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        super.reset();
        myDeathTime = 15;
    }
}
