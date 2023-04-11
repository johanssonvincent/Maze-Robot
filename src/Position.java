/**
 * @author  Vincent Johansson
 * @since   2022-04-13
 */

import java.util.Objects;

/**
 * Position Class
 * Container for an int coordinate pair (X.Y)
 * Has the ability to query for neighbouring coordinates.
 */
public class Position {

    private final int x;
    private final int y;

    /**
     * Constructor for the class.
     * Contains two int variables to be able to
     * store a coordinate pair.
     *
     * @param   y   Integer
     * @param   x   Integer
     */
    public Position(int y, int x){
        this.x = x;
        this.y = y;
    }

    /**
     * Method to get x coordinate from
     * a Position class object.
     *
     * @return  x   int
     */
    public int getX(){
        return this.x;
    }

    /**
     * Method to get y coordinate from
     * a Position class object.
     *
     * @return  y   int
     */
    public int getY(){
        return this.y;
    }

    /**
     * Method to get coordinates of
     * the node south of current position.
     *
     * @return  south   Position object
     */
    public Position getPosToSouth(){
        return new Position(this.getY()+1, this.getX());
    }

    /**
     * Method to get coordinates of
     * the node north of current position.
     *
     * @return  north   Position object
     */
    public Position getPosToNorth(){
        return new Position(this.getY()-1, this.getX());
    }

    /**
     * Method to get coordinates of
     * the node west of current position.
     *
     * @return  west    Position object
     */
    public Position getPosToWest(){
        return new Position(this.getY(), this.getX()-1);
    }

    /**
     * Method to get coordinates of
     * the node east of current position.
     *
     * @return  east    Position object
     */
    public Position getPosToEast(){
        return new Position(this.getY(), this.getX()+1);
    }

    /**
     *  Override method of equals.
     *  Compares two objects to see if they're equal.
     *
     *  @param o Class object
     *  @return  boolean true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     *  Override method to create a HashCode for a position object.
     *
     *  @return hash object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
