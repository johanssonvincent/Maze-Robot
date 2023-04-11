/**
 * @author  Vincent Johansson
 * @since   2022-05-27
 */

/**
 *  The interface for the robot.
 */
interface Robot {
    void move();
    Position getPosition();
    boolean hasReachedGoal();
}
