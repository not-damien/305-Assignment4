package model;


import java.util.Map;

public abstract class AbstractVehicle implements Vehicle{
    int myX,myY;
    boolean isAlive;
    private Direction myDir;
    String myAliveIcon;
    String myDeathIcon;
    public AbstractVehicle(int theX, int theY, Direction north) {
        myX = theX;
        myY = theY;
        isAlive = true;
    }
    @Override
    public Direction getDirection() {
        return myDir;
    }
    @Override
    public void reset() {
       isAlive = true;
    }


    @Override
    public boolean isAlive() {
        return isAlive;
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
    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }
    @Override
    public String getImageFileName() {
        if(isAlive()){
            return myAliveIcon;
        }else
            return myDeathIcon;
    }

}
