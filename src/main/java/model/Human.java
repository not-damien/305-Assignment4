package model;

public class Human extends AbstractVehicle{

    public Human(int theX, int theY, Direction theDir) {
        super(theX, theY,theDir );
        myAliveIcon = "human.gif";
        myDeathIcon = "human_dead.gif";
    }

}
