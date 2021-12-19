package model;


public abstract class AbstractVehicle implements Vehicle{
    int myX,myY;
    boolean isAlive;
    protected Direction myDir;
    String myAliveIcon;
    String myDeathIcon;
    int myDeathTime;
    final int initialX, initialY;
    protected AbstractVehicle(int theX, int theY, Direction theDir) {
        myX = theX;
        myY = theY;
        isAlive = true;
        myDir = theDir;
        initialX = theX;
        initialY = theY;
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
        if(myDeathTime  == 0 ){
            reset();
        }
    }

    @Override
    public void reset() {
        myY = initialY;
        myX = initialX;
        isAlive = true;
    }
}
