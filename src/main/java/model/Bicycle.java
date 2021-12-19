package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bicycle extends AbstractVehicle {
    final List<Terrain> validTerrain = new ArrayList<>();

    public Bicycle(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "bicycle.gif";
        myDeathIcon = "bicycle_dead.gif";
        myDeathTime = 35;
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.TRAIL);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight){
        if(validTerrain.contains(theTerrain)){
            if(theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.LIGHT){
                return theLight == Light.GREEN;
            }else{
                return true;
            }
        }
        return false;
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Terrain forward = theNeighbors.get(getDirection());
        Terrain left = theNeighbors.get(getDirection().left());
        Terrain right = theNeighbors.get(getDirection().right());
        if(forward == Terrain.TRAIL){
            return getDirection();
        }
        if(left == Terrain.TRAIL){
            return getDirection().left();
        }
        if(right == Terrain.TRAIL){
            return getDirection().right();
        }
        if(validTerrain.contains(forward)){
            return getDirection();
        }
        if(validTerrain.contains(left)){
            return getDirection().left();
        }
        if(validTerrain.contains(right)){
            return getDirection().right();
        }

        return getDirection().reverse();
    }

    /**
     *  If it collides with a truck, car, taxi, or ATV. It stays in repair shop for 35 moves.
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther) {
        String name = theOther.getClass().getSimpleName();
        if(name.equalsIgnoreCase("truck") || name.equalsIgnoreCase("atv") ||
                name.equalsIgnoreCase("car") || name.equalsIgnoreCase("Taxi")){
            isAlive = false;
        }
    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        super.reset();
        myDeathTime = 35;
    }

}
