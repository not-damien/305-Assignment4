package model;

public class Atv extends AbstractVehicle{
    public Atv(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "atv.gif";
        myDeathIcon = "atv_dead.gif";
    }
    //TODO
}
