package model;


import java.util.Map;

public abstract class AbstractVehicle implements Vehicle{
    int myX,myY;
    boolean isAlive;
    private Direction myDir;

    public AbstractVehicle(int theX, int theY, Direction north) {
        myX = theX;
        myY = theY;
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return false;
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return null;
    }

    @Override
    public void collide(Vehicle theOther) {

    }

    @Override
    public int getDeathTime() {
        return 0;
    }

    @Override
    public String getImageFileName() {
        return "truck.gif";
    }

    @Override
    public Direction getDirection() {
        return myDir;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void poke() {

    }

    @Override
    public void reset() {

    }



    @Override
    public void setDirection(Direction theDir) {
            myDir = theDir;
    }

    @Override
    public void setX(int theX) {
        myX = theX;
    }

    @Override
    public void setY(int theY) {
        myY = theY;
    }
}
