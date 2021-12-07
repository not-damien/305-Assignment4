package model;


import java.util.Map;

public abstract class AbstractVehicle implements Vehicle{
    public AbstractVehicle(int i, int i1, Direction north) {
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
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void poke() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void setDirection(Direction theDir) {

    }

    @Override
    public void setX(int theX) {

    }

    @Override
    public void setY(int theY) {

    }
}
