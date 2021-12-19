package model;


import java.util.ArrayList;
import java.util.List;

public class Taxi extends Car {
    public Taxi(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "taxi.gif";
        myDeathIcon = "taxi_dead.gif";
    }

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
            }if (theTerrain == Terrain.STREET){
                return true;
            }
        }
        return false;
    }
}
