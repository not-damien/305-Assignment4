package model;

import java.util.Map;

import static model.Direction.*;
import static model.Terrain.CROSSWALK;
import static model.Terrain.GRASS;

/**
 * Human
 * • Constructor:public Human(int theX, int theY, Direction theDir)
 * • Images: human.gif (alive), human_dead.gif (dead)
 * • Movement behavior:
 * o Humans  move  in  a  random  direction  (straight,  left,  or  right),  always  on  grass  or
 * crosswalks.
 * o A human never reverses direction unless there is no other option.
 * o If a human is next to a crosswalk it will always choose to turn to face in the direction of the crosswalk.
 * (The  map  of  terrain  will  never  contain  crosswalks  that  are  so  close  together  that  a  human  might  be
 * adjacent to more than one at the same time.)
 * o Humans  do  not  travel  through  crosswalks  when  the crosswalk  light  is  green.  If a  human  is facing a
 * green crosswalk, it will wait until the light changes to yellow and then cross through the crosswalk. The
 * human will not turn to avoid the crosswalk.
 * o Humans travel through crosswalks when the crosswalk light is yellow or red.
 * • Collision behavior: A human dies if it collides with any living vehicle except another human and stays dead for
 * 45 moves.
 */
public class Human extends AbstractVehicle{

    public Human(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "human.gif";
        myDeathIcon = "human_dead.gif";
        myDeathTime = 45;
    }


    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return (theLight != Light.GREEN && theTerrain == CROSSWALK) || (theTerrain == Terrain.GRASS);
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction Reverse = getDirection().reverse();
        if (theNeighbors.containsValue(CROSSWALK)){
            if(CROSSWALK == theNeighbors.get(NORTH) && Reverse != NORTH)
                return NORTH;
            else if(CROSSWALK == theNeighbors.get(SOUTH)&& Reverse != SOUTH)
                return SOUTH;
            else if(CROSSWALK == theNeighbors.get(EAST) && Reverse != EAST)
                return EAST;
            else if(CROSSWALK == theNeighbors.get(WEST) && Reverse != WEST)
                return WEST;
        }else if(theNeighbors.get(getDirection()) != GRASS &&
                theNeighbors.get(getDirection().left()) != GRASS &&
                theNeighbors.get(getDirection().right()) != GRASS) {
            return getDirection().reverse();
        }
        Direction temp = random();
        if (Reverse != temp && theNeighbors.get(temp) == GRASS) {
                return temp;
            }
        return null;
        }
    @Override
    public void collide(Vehicle theOther) {
        if (theOther.getClass() != this.getClass()){
            isAlive = false;
            myDeathTime = 45;
        }
    }
    @Override
    public void reset() {
        super.reset();
        myDeathTime = 45;
    }


}
