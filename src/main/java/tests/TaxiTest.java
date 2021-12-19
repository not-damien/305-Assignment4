package tests;

import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TaxiTest {//todo
    private static final int TRIES_FOR_RANDOMNESS = 50;
    @Test
    public void testTaxiConstructor() {
        final Taxi t = new Taxi(10, 11, Direction.NORTH);

        assertEquals("taxi x coordinate not initialized correctly!", 10, t.getX());
        assertEquals("taxi y coordinate not initialized correctly!", 11, t.getY());
        assertEquals("taxi direction not initialized correctly!",
                Direction.NORTH, t.getDirection());
        assertEquals("taxi death time not initialized correctly!", 15, t.getDeathTime());
        assertTrue("taxi isAlive() fails initially!", t.isAlive());
    }

    @Test
    public void testCanPass() {

        //only travels on streets and through lights and crosswalks,
        // so we need to test those conditions


        //stops for (temporarily) red crosswalk lights. If a crosswalk light is immediately ahead of the taxi and
        //the crosswalk light is red, the Taxi stays still and does not move for 3 clock cycles or until the crosswalk
        //light  turns  green,  whichever  occurs  first.  It  does  not  turn  to  avoid  the  crosswalk  light.  When  the
        //crosswalk light turns green, or after 3 time steps, whichever happens first, the taxi resumes its original
        //direction. A taxi will drive through yellow or green crosswalk lights without stopping.

        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);

        final Taxi taxman = new Taxi(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {
                    // Taxi can pass STREET under any light condition
                    assertTrue("Taxi should be able to pass Street"
                                    + ", with light " + currentLightCondition,
                            taxman.canPass(destinationTerrain, currentLightCondition));
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    // Taxi can pass CROSSWALK
                    // unless the crosswalk is red

                    if (currentLightCondition == Light.RED) {
                        assertFalse("taxis should be able to pass " + destinationTerrain
                                        + ", with light " + currentLightCondition,
                                taxman.canPass(destinationTerrain,
                                        currentLightCondition));
                    } else { // light is green or yellow
                        assertTrue("taxis should not be able to pass " + destinationTerrain
                                        + ", with light " + currentLightCondition,
                                taxman.canPass(destinationTerrain,
                                        currentLightCondition));
                    }
                } else if (destinationTerrain == Terrain.LIGHT) {
                    if (currentLightCondition == Light.RED) {//taxis stop for red traffic lights
                        assertFalse("Taxi should not be able to travel" + destinationTerrain
                                        + " ,with Light" + currentLightCondition,
                                taxman.canPass(destinationTerrain, currentLightCondition));
                    } else {
                        assertTrue("Taxi should be able to pass " + destinationTerrain
                                        + ", with light " + currentLightCondition,
                                taxman.canPass(destinationTerrain,
                                        currentLightCondition));
                    }
                } else if (!validTerrain.contains(destinationTerrain)) {

                    assertFalse("Taxis should NOT be able to pass " + destinationTerrain
                                    + ", with light " + currentLightCondition,
                            taxman.canPass(destinationTerrain, currentLightCondition));
                }
            }
        }
    }

    /**
     * Test method for {@link Taxi#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testDriveForwardSurroundedByStreet() {
        //prefers to drive straight ahead if it can.  If it cannot move straight ahead, it turns left if possible; is it
       // cannot turn left, it turns right if possible; as a last resort, it turns around.
        final Map<Direction, Terrain> neighbors = new HashMap<>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        final Taxi tax = new Taxi(0, 0, Direction.NORTH);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = tax.chooseDirection(neighbors);

            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }

        assertTrue("The Taxi failed to go straight",
                seenNorth);

        assertFalse("Taxi chose not to go straight when possible!",
                seenSouth || seenEast || seenWest);
    }


    /**
     * Test method for {@link Taxi#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionMustReverse() {
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {

                final Map<Direction, Terrain> neighbors = new HashMap<>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);

                final Taxi tax = new Taxi(0, 0, Direction.NORTH);
                // the Human must reverse and go SOUTH
                assertEquals("taxi chooseDirection() failed "
                                + "when reverse was the only valid choice!",
                        Direction.SOUTH, tax.chooseDirection(neighbors));
            }

        }
    }

    @Test
    public void goLeft() {
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {

                final Map<Direction, Terrain> neighbors = new HashMap<>();
                neighbors.put(Direction.WEST, Terrain.STREET);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, Terrain.STREET);
                neighbors.put(Direction.SOUTH, Terrain.STREET);

                final Taxi taxi = new Taxi(0, 0, Direction.NORTH);
                assertEquals("Taxi chooseDirection() failed "
                                + "left was the best option and the taxi did not go left!",
                        Direction.WEST, taxi.chooseDirection(neighbors));
            }
        }
    }
    @Test
    public void goRight(){
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {

                final Map<Direction, Terrain> neighbors = new HashMap<>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, Terrain.STREET);
                neighbors.put(Direction.SOUTH, Terrain.STREET);

                final Taxi tax = new Taxi(0, 0, Direction.NORTH);
                // the Human must reverse and go SOUTH
                assertEquals("Taxi chooseDirection() failed "
                                + "Right was the best option and the taxi did not go Right",
                        Direction.EAST, tax.chooseDirection(neighbors));
            }
        }
    }
}

