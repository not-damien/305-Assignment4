
package tests;

import model.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit tests for class Truck.
 *
 * @author Damien Cruz
 */
public class AtvTest {

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /** Test method for Human constructor. */
    @Test
    public void testAtvConstructor() {
        final Atv t = new Atv(10, 11, Direction.NORTH);

        assertEquals("Atv x coordinate not initialized correctly!", 10, t.getX());
        assertEquals("Atv y coordinate not initialized correctly!", 11, t.getY());
        assertEquals("Atv direction not initialized correctly!",
                Direction.NORTH, t.getDirection());
        assertEquals("Atv death time not initialized correctly!", 25, t.getDeathTime());
        assertTrue("Atv isAlive() fails initially!", t.isAlive());
    }

    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Atv h = new Atv(10, 11, Direction.NORTH);

        h.setX(12);
        assertEquals("Atv setX failed!", 12, h.getX());
        h.setY(13);
        assertEquals("Atv setY failed!", 13, h.getY());
        h.setDirection(Direction.SOUTH);
        assertEquals("Atv setDirection failed!", Direction.SOUTH, h.getDirection());
    }

    /**
     * Test method for {@link Atv#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        // atvs can travel on any terrain except walls
        // so we need to test those conditions

        // Atvs should only reverse direction if no other option is available
        // so we need to be sure to test that requirement also
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain != Terrain.WALL) {
                    // Atvs can pass under any light condition
                    assertTrue("Atvs should be able to pass " + destinationTerrain
                                    + ", with light " + currentLightCondition,
                            atv.canPass(destinationTerrain, currentLightCondition));
                }else{
                    assertFalse("ATVs Should not be able to pass" + destinationTerrain
                    + "with light" + currentLightCondition, atv.canPass(destinationTerrain, currentLightCondition));
                }
            }
        }
    }

    /**
     * Test method for {@link Atv#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByStreet() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        final Atv atv = new Atv(0, 0, Direction.NORTH);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = atv.chooseDirection(neighbors);

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

        assertTrue("Atv chooseDirection() fails to select randomly "
                        + "among all possible valid choices!",
                seenWest && seenNorth && seenEast);

        assertFalse("Truck chooseDirection() reversed direction when not necessary!",
                seenSouth);
    }


    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionMustReverse() {
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, Terrain.WALL);
                neighbors.put(Direction.NORTH, Terrain.WALL);
                neighbors.put(Direction.EAST, Terrain.WALL);
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                final Atv atv = new Atv(0, 0, Direction.NORTH);
                // the atv must reverse and go SOUTH
                assertEquals("Truck chooseDirection() failed "
                                + "when reverse was the only valid choice!",
                        Direction.SOUTH, atv.chooseDirection(neighbors));
        }
    }
