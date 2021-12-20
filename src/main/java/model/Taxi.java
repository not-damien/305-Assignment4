package model;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Taxi.
 * taxi extends car because apart from canPass() method all behaviour is the same
 */
public class Taxi extends Car {
    /**
     * Instantiates a new Taxi.
     *
     * @param theX   the x
     * @param theY   the y
     * @param theDir the dir
     */
    public Taxi(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "taxi.gif";
        myDeathIcon = "taxi_dead.gif";
    }

    /**
     * taxis stop at all red lights
     * can drive on streets and through crosswalks and lights
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return true if tax can pass
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);

        if(validTerrain.contains(theTerrain)){
            if(theTerrain == Terrain.LIGHT){
                return theLight != Light.RED;
            }

            if(theTerrain == Terrain.CROSSWALK){
                return theLight != Light.RED;
            }
            return theTerrain == Terrain.STREET;
        }
        return false;
    }
}
