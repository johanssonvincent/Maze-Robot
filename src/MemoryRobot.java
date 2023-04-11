/**
 *  @author  Vincent Johansson
 *  @since   2022-05-15
 */


import java.util.Stack;
import java.util.HashMap;

/**
 *  MemoryRobot class
 *  A robot implementation where the robot remembers previously
 *  visited positions and the path it has taken. It then tries to
 *  walk a path without backtracking to previously visited positions.
 */
public class MemoryRobot implements Robot{

    /* Variable declarations. */
    private Position position;
    private final Stack<Position> pathMemory;
    private final HashMap<Integer, Position> positionMemory;
    private final Maze maze;

    /**
     *  Class constructor.
     *
     *  @param  maze    Maze class object
     */
    public MemoryRobot(Maze maze){
        this.maze = maze;

        /* Creates a stack to store path and save start position as current position. */
        pathMemory = new Stack<>();
        setPosition(maze.getStart());

        /* Creates a HashMap to store visited positions. */
        positionMemory = new HashMap<>();
        positionMemory.put(position.hashCode(), position);
    }

    /**
     *  Move method that implements a "depth first search"
     *  variant of move. Move priority is N, E, S, W.
     *  Prioritizes to move to a position that has not yet been visited.
     *  Backtracks the path taken if no such neighbouring position exists.
     */
    @Override
    public void move(){

        if(!maze.isGoal(position)){
            if(maze.isMovable(position.getPosToEast())){
                if(!positionMemory.containsValue(position.getPosToEast())){
                    pathMemory.push(position);
                    setPosition(position.getPosToEast());
                    positionMemory.put(position.hashCode(), position);
                    return;
                }
            }

            if(maze.isMovable(position.getPosToWest())){
                if(!positionMemory.containsValue(position.getPosToWest())){
                    pathMemory.push(position);
                    setPosition(position.getPosToWest());
                    positionMemory.put(position.hashCode(), position);
                    return;
                }
            }

            if(maze.isMovable(position.getPosToSouth())){
                if(!positionMemory.containsValue(position.getPosToSouth())){
                    pathMemory.push(position);
                    setPosition(position.getPosToSouth());
                    positionMemory.put(position.hashCode(), position);
                    return;
                }
            }

            if(maze.isMovable(position.getPosToNorth())){
                if(!positionMemory.containsValue(position.getPosToNorth())){
                    pathMemory.push(position);
                    setPosition(position.getPosToNorth());
                    positionMemory.put(position.hashCode(), position);
                    return;
                }
            }

            /*
                If robot has previously visited all the neighbouring positions
                backtrack the path it has used until an unvisited neighbour appears.
                Do this as long as pathMemory isn't empty.
            */
            if(!pathMemory.empty()){
                setPosition(pathMemory.peek());
                pathMemory.pop();
            }
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
     *  Method that checks if robot has reached
     *  the goal position.
     *
     *  @return  boolean     true/false
     */
    @Override
    public boolean hasReachedGoal(){
        return maze.isGoal(position);
    }

    /**
     *  Method that prints the coordinates for the current position.
     *  Used for private debugging.
     */
    private void printPosition(){

        /* int variables to shorten line length. */
        int x = position.getX();
        int y = position.getY();

        String str = String.format("Current coordinate (x,y): (%d,%d)", x, y);

        System.out.println(str);
    }
}
