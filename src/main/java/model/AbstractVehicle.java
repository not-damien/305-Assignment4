package model;


import java.util.Map;
import java.util.Random;

public abstract class AbstractVehicle implements Vehicle{
    int myX,myY;
    boolean isAlive;
    private Direction myDir;
    String myAliveIcon;
    String myDeathIcon;
    int myDeathTime;
    final static Random rando = new Random();
    protected AbstractVehicle(int theX, int theY, Direction theDir) {
        myX = theX;
        myY = theY;
        isAlive = true;
        myDir = theDir;
    }
    @Override
    public Direction getDirection() {
        return myDir;
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
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    @Override
    public void poke() {
        myDeathTime--;
    }

}
