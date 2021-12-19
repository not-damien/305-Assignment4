package tests;

import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CarTest {//complete

        /**
         * The number of times to repeat a test to have a high probability that all
         * random possibilities have been explored.
         */
        private static final int TRIES_FOR_RANDOMNESS = 50;

        /**
         * Test method for car constructor.
         */
        @Test
        public void testCarConstructor() {
                final Car t = new Car(10, 11, Direction.NORTH);

                assertEquals("Car x coordinate not initialized correctly!", 10, t.getX());
                assertEquals("Car y coordinate not initialized correctly!", 11, t.getY());
                assertEquals("Car direction not initialized correctly!",
                        Direction.NORTH, t.getDirection());
                assertEquals("Car death time not initialized correctly!", 15, t.getDeathTime());
                assertTrue("Car isAlive() fails initially!", t.isAlive());
        }

        /**
         * Test method for Car setters.
         */
        @Test
        public void testCarSetters() {
                final Car h = new Car(10, 11, Direction.NORTH);

                h.setX(12);
                assertEquals("Car setX failed!", 12, h.getX());
                h.setY(13);
                assertEquals("Car setY failed!", 13, h.getY());
                h.setDirection(Direction.SOUTH);
                assertEquals("Car setDirection failed!", Direction.SOUTH, h.getDirection());
        }

        /**
         * Test method for {@link Truck#canPass(Terrain, Light)}.
         */
        @Test
        public void testCanPass() {

                //only travels on streets and through lights and crosswalks,
                // so we need to test those conditions


                //stops for red lights; if a traffic light is immediately ahead of the car and the light is  red, the car stays
                //still and does not move. It does not turn to avoid the light. When the light turns green, the car resumes
                //its original direction.

                //ignores yellow and green lights.
                // stops for red and yellow crosswalk lights but drive through green crosswalk lights without stopping.

                final List<Terrain> validTerrain = new ArrayList<>();
                validTerrain.add(Terrain.STREET);
                validTerrain.add(Terrain.LIGHT);
                validTerrain.add(Terrain.CROSSWALK);

                final Car car = new Car(0, 0, Direction.NORTH);
                // test each terrain type as a destination
                for (final Terrain destinationTerrain : Terrain.values()) {
                        // try the test under each light condition
                        for (final Light currentLightCondition : Light.values()) {
                                if (destinationTerrain == Terrain.STREET) {
                                        // cars can pass STREET under any light condition
                                        assertTrue("Cars should be able to pass Street"
                                                        + ", with light " + currentLightCondition,
                                                car.canPass(destinationTerrain, currentLightCondition));
                                } else if (destinationTerrain == Terrain.CROSSWALK) {
                                        // cars can pass CROSSWALK
                                        // if the light is green, but not yellow or red

                                        if (currentLightCondition == Light.GREEN) {
                                                assertTrue("Cars should be able to pass " + destinationTerrain
                                                                + ", with light " + currentLightCondition,
                                                        car.canPass(destinationTerrain,
                                                                currentLightCondition));
                                        } else { // light is red or yellow
                                                assertFalse("Cars should not be able to pass " + destinationTerrain
                                                                + ", with light " + currentLightCondition,
                                                        car.canPass(destinationTerrain,
                                                                currentLightCondition));
                                        }
                                } else if (destinationTerrain == Terrain.LIGHT) {
                                        if (currentLightCondition == Light.RED) {//cars stop for red traffic lights
                                                assertFalse("cars should not be able to travel" + destinationTerrain
                                                                + " ,with Light" + currentLightCondition,
                                                        car.canPass(destinationTerrain, currentLightCondition));
                                        } else {
                                                assertTrue("Cars should be able to pass " + destinationTerrain
                                                                + ", with light " + currentLightCondition,
                                                        car.canPass(destinationTerrain,
                                                                currentLightCondition));
                                        }
                                } else if (!validTerrain.contains(destinationTerrain)) {

                                        assertFalse("Cars should NOT be able to pass " + destinationTerrain
                                                        + ", with light " + currentLightCondition,
                                                car.canPass(destinationTerrain, currentLightCondition));
                                }
                        }
                }
        }

        /**
         * Test method for {@link Car#chooseDirection(java.util.Map)}.
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

                final Car car = new Car(0, 0, Direction.NORTH);

                for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
                        final Direction d = car.chooseDirection(neighbors);

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
         * Test method for {@link Car#chooseDirection(java.util.Map)}.
         */
        @Test
        public void testChooseDirectionMustReverse() {//todo not configured for car
                for (final Terrain t : Terrain.values()) {
                        if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {

                                final Map<Direction, Terrain> neighbors = new HashMap<>();
                                neighbors.put(Direction.WEST, t);
                                neighbors.put(Direction.NORTH, t);
                                neighbors.put(Direction.EAST, t);
                                neighbors.put(Direction.SOUTH, Terrain.STREET);

                                final Car car = new Car(0, 0, Direction.NORTH);
                                // the Human must reverse and go SOUTH
                                assertEquals("Car chooseDirection() failed "
                                                + "when reverse was the only valid choice!",
                                        Direction.SOUTH, car.chooseDirection(neighbors));
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

                                final Car car = new Car(0, 0, Direction.NORTH);
                                // the Human must reverse and go SOUTH
                                assertEquals("Car chooseDirection() failed "
                                                + "left was the best option and the car did not go left!",
                                        Direction.WEST, car.chooseDirection(neighbors));
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

                                final Car car = new Car(0, 0, Direction.NORTH);
                                // the Human must reverse and go SOUTH
                                assertEquals("Car chooseDirection() failed "
                                                + "Right was the best option and the car did not go Right",
                                        Direction.EAST, car.chooseDirection(neighbors));
                        }
                }
        }
}
