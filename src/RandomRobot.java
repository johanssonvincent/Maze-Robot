/**
 * @author  Vincent Johansson
 * @since   2022-04-18
 */

import java.util.ArrayList;
import java.util.Random;

/**
 *  RandomRobot class
 *  A robot implementation where the robot makes
 *  random moves while it moves through the maze.
 */
public class RandomRobot implements Robot{

    /* Variable declarations. */
    private Position position;
    private Position previousPosition;
    private final Maze maze;

    /**
     *  Class constructor.
     *
     *  @param   maze        Maze class object
     */
    public RandomRobot(Maze maze){
        this.maze = maze;
        setPosition(maze.getStart());
        setPreviousPosition(maze.getStart());
    }

    /**
     *  Method handling the robot's ability to move through the given maze.
     */
    @Override
    public void move(){

        /* Declarations of variables needed in the move() method. */
        Position[] neighbours = new Position[4];
        ArrayList<Position> posList = new ArrayList<>();
        Random rand = new Random();

        /* Save neighbouring positions in Position array. */
        neighbours[0] = position.getPosToEast();
        neighbours[1] = position.getPosToSouth();
        neighbours[2] = position.getPosToWest();
        neighbours[3] = position.getPosToNorth();

        /*
            Check which of the neighbouring positions are eligible moves.
            Save the ones that are in Position ArrayList.
        */
        for (Position position: neighbours){
            if(maze.isMovable(position) && !position.equals(previousPosition)){
                posList.add(position);
            }
        }

        /*
            If there are more than 1 eligible moves in posList
            move to a randomized position from the list.
            If there's only one in the posList, move to that position.
            And if none of the neighbouring positions are eligible
            return to previous position.
         */
        if(posList.size() > 1){
            setPreviousPosition(getPosition());
            setPosition(posList.get(rand.nextInt(posList.size())));
        }else if(posList.size() == 1){
            setPreviousPosition(getPosition());
            setPosition(posList.get(0));
        }else{
            Position temp = getPosition();
            setPosition(previousPosition);
            setPreviousPosition(temp);
        }
    }

    /**
     *  Method that returns current position.
     *
     *  @return  this.position   current Position object
     */
    @Override
    public Position getPosition(){
        return this.position;
    }

    /**
     *  Method that sets a new position for the robot.
     *
     *  @param   position    Position object
     */
    private void setPosition(Position position){
        this.position = position;
    }

    /**
     *  Method that sets previousPosition variable.
     *
     *  @param   position    Position object
     */
    private void setPreviousPosition(Position position){
        this.previousPosition = position;
    }

    /**
     *  Method that checks if robot has reached the goal position.
     *
     *  @return  boolean     true/false
     */
    @Override
    public boolean hasReachedGoal(){
        return maze.isGoal(position);
    }

    /**
     *  Method that prints the coordinates for the current position.
     */
    private void printPosition(){

        /* int variables to shorten line length. */
        int x = position.getX();
        int y = position.getY();

        String str = String.format("Current coordinate (x,y): (%d,%d)", x, y);

        System.out.println(str);
    }
}
