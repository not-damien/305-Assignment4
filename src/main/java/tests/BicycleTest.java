package tests;

import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BicycleTest {
    //can travel on streets and through lights and crosswalk lights, but they prefer to travel on trails.
    // If the terrain in front of a bicycle is a trail, the bicycle always goes straight ahead in the direction it is
    //facing.
    // Trails are guaranteed to be straight (horizontal or vertical) lines that end at streets, and a bicycle


    //If a bicycle is not facing a trail, but there is a trail either to the left or to the right
    // then the bicycle moves in that direction.


    //D. If there is no trail straight ahead, to the left, or to the right, the bicycle will move straight ahead
    //on a street (or light or crosswalk light) if it can.  If it cannot move straight ahead, it turns left if possible;
    //if it cannot turn left, it turns right if possible. As a last resort, the bicycle turns around.

    //E. ignores green lights.

    //F. stops for yellow and red lights; if a traffic light or crosswalk light is immediately ahead of the bicycle
    //and the light is not green, the bicycle stays still and does not move unless a trail is to the left or right.
    //If a bicycle is facing a red or yellow light and there is a trail to the left or right, the bicycle will turn to
    //face the trail.




    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /**
     * Test method for car constructor.
     */
    @Test
    public void testBicycleConstructor() {
        final Bicycle t = new Bicycle(10, 11, Direction.NORTH);

        assertEquals("Bicycle x coordinate not initialized correctly!", 10, t.getX());
        assertEquals("Bicycle y coordinate not initialized correctly!", 11, t.getY());
        assertEquals("Bicycle direction not initialized correctly!",
                Direction.NORTH, t.getDirection());
        assertEquals("Bicycle death time not initialized correctly!", 35, t.getDeathTime());
        assertTrue("Bicycle isAlive() fails initially!", t.isAlive());
    }

    /**
     * Test method for Bicycle setters.
     */
    @Test
    public void testBicycleSetters() {
        final Bicycle h = new Bicycle(10, 11, Direction.NORTH);

        h.setX(12);
        assertEquals("Bicycle setX failed!", 12, h.getX());
        h.setY(13);
        assertEquals("Bicycle setY failed!", 13, h.getY());
        h.setDirection(Direction.SOUTH);
        assertEquals("Bicycle setDirection failed!", Direction.SOUTH, h.getDirection());
    }

    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        //can travel Street, light, crosswalk and trail;
        //stops yellow and red traffic and crosswalk
        //
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.TRAIL);

        final Bicycle bike = new Bicycle(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET || destinationTerrain == Terrain.TRAIL) {
                    // cars can pass STREET under any light condition
                    assertTrue("Bicycles should be able to pass Street"
                                    + ", with light " + currentLightCondition,
                            bike.canPass(destinationTerrain, currentLightCondition));
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    // cars can pass CROSSWALK
                    // if the light is green, but not yellow or red

                    if (currentLightCondition == Light.GREEN) {
                        assertTrue("Bicycles should be able to pass " + destinationTerrain
                                        + ", with light " + currentLightCondition,
                                bike.canPass(destinationTerrain,
                                        currentLightCondition));
                    } else { // light is red or yellow
                        assertFalse("Bicycles should not be able to pass " + destinationTerrain
                                        + ", with light " + currentLightCondition,
                                bike.canPass(destinationTerrain,
                                        currentLightCondition));
                    }
                } else if (destinationTerrain == Terrain.LIGHT) {
                    if (currentLightCondition == Light.GREEN) {//cars stop for red traffic lights
                        assertTrue("cars should be able to travel" + destinationTerrain
                                        + " ,with Light" + currentLightCondition,
                                bike.canPass(destinationTerrain, currentLightCondition));
                    } else {
                        assertFalse("Bicycles not should be able to pass " + destinationTerrain
                                        + ", with light " + currentLightCondition,
                                bike.canPass(destinationTerrain,
                                        currentLightCondition));
                    }
                } else if (!validTerrain.contains(destinationTerrain)) {

                    assertFalse("Bicycles should NOT be able to pass " + destinationTerrain
                                    + ", with light " + currentLightCondition,
                            bike.canPass(destinationTerrain, currentLightCondition));
                }
            }
        }
    }

    /**
     * Test method for {@link Bicycle#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testDriveForwardSurroundedByStreet() {
        final Map<Direction, Terrain> neighbors = new HashMap<>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        final Bicycle bicycle = new Bicycle(0, 0, Direction.NORTH);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = bicycle.chooseDirection(neighbors);

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

        assertTrue("The car failed to go straight",
                seenNorth);

        assertFalse("car chose not to go straight when possible!",
                seenSouth || seenEast || seenWest);
    }


    /**
     * Test method for {@link Bicycle#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionMustReverse() {
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT && t != Terrain.TRAIL) {

                final Map<Direction, Terrain> neighbors = new HashMap<>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);

                final Bicycle car = new Bicycle(0, 0, Direction.NORTH);
                // the Human must reverse and go SOUTH
                assertEquals("Bicycle chooseDirection() failed "
                                + "when reverse was the only valid choice!",
                        Direction.SOUTH, car.chooseDirection(neighbors));
            }

        }
    }

    @Test
    public void goLeft() {
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT && t != Terrain.TRAIL) {

                final Map<Direction, Terrain> neighbors = new HashMap<>();
                neighbors.put(Direction.WEST, Terrain.TRAIL);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, Terrain.STREET);
                neighbors.put(Direction.SOUTH, Terrain.STREET);

                final Bicycle car = new Bicycle(0, 0, Direction.NORTH);
                // the Human must reverse and go SOUTH
                assertEquals("Bicycle chooseDirection() failed "
                                + "left was the best option and the car did not go left!",
                        Direction.WEST, car.chooseDirection(neighbors));
            }
        }
    }
    @Test
    public void goRight(){
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT && t != Terrain.TRAIL) {

                final Map<Direction, Terrain> neighbors = new HashMap<>();
                neighbors.put(Direction.WEST, Terrain.STREET);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, Terrain.TRAIL);
                neighbors.put(Direction.SOUTH, Terrain.STREET);

                final Bicycle bike = new Bicycle(0, 0, Direction.NORTH);
                // the Human must reverse and go SOUTH
                assertEquals("Bicycle chooseDirection() failed "
                                + "Right was the best option and the bike did not go Right",
                        Direction.EAST, bike.chooseDirection(neighbors));
            }
        }
    }
}
