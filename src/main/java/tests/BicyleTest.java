package tests;

public class BicyleTest {//todo
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
}
