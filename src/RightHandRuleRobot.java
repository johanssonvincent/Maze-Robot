/**
 * @author  Vincent Johansson
 * @since   2022-05-15
 */

/**
 *  RightHandRuleRobot class
 *  A robot implementation where the robot always has
 *  a wall on the right side while it moves through the maze.
 */
public class RightHandRuleRobot implements Robot{

    /* enums for direction */
    enum Direction{
        NORTH,
        WEST,
        SOUTH,
        EAST
    }

    /* Variable declarations. */
    private Position position;
    private Position previousPosition;
    private final Maze maze;
    private Direction dir;

    /**
     *  Class constructor.
     *
     *  @param maze maze
     */
    public RightHandRuleRobot(Maze maze){
        this.maze = maze;
        setPosition(maze.getStart());
        setPreviousPosition(maze.getStart());
        initializeDirection();
    }

    /**
     *  Move method that implements "right-hand rule"
     *  of moving where a wall always is on the right
     *  of the robot.
     */
    @Override
    public void move(){

        if(!maze.isGoal(position)){
            if(movableDirection(dir)){
                switch(dir){
                    case NORTH:
                        setPreviousPosition(getPosition());
                        setPosition(position.getPosToNorth());
                        break;
                    case WEST:
                        setPreviousPosition(getPosition());
                        setPosition(position.getPosToWest());
                        break;
                    case SOUTH:
                        setPreviousPosition(getPosition());
                        setPosition(position.getPosToSouth());
                        break;
                    case EAST:
                        setPreviousPosition(getPosition());
                        setPosition(position.getPosToEast());
                        break;
                }
            }else{
                findMoveDirection();
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
     *  Method that sets previousPosition variable.
     *
     *  @param   position    Position object
     */
    private void setPreviousPosition(Position position){
        this.previousPosition = position;
    }

    /**
     * Method that checks if the next position in a direction
     * is movable or not.
     *
     * @param dir   Direction the robot is moving
     * @return  true/false
     */
    private boolean movableDirection(Direction dir){
        try{
            switch(dir){
                case NORTH:
                    if(!maze.isMovable(this.getPosition().getPosToEast())){
                        return maze.isMovable(this.getPosition().getPosToNorth());
                    }
                    return false;
                case WEST:
                    if(!maze.isMovable(this.getPosition().getPosToNorth())){
                        return maze.isMovable(this.getPosition().getPosToWest());
                    }
                    return false;
                case SOUTH:
                    if(!maze.isMovable(this.getPosition().getPosToWest())){
                        return maze.isMovable(this.getPosition().getPosToSouth());
                    }
                    return false;
                case EAST:
                    if(!maze.isMovable(this.getPosition().getPosToSouth())){
                        return maze.isMovable(this.getPosition().getPosToEast());
                    }
                    return false;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }

        return false;
    }

    /**
     * Method that finds a new movable direction if
     * the next position in the current direction isn't movable.
     */
    private void findMoveDirection(){
        switch(dir){
            case NORTH:
                if(maze.isMovable(getPosition().getPosToEast())){
                    dir = Direction.EAST;
                    setPreviousPosition(getPosition());
                    setPosition(getPosition().getPosToEast());
                }else if(maze.isMovable(getPosition().getPosToWest())){
                    dir = Direction.WEST;
                    setPreviousPosition(getPosition());
                    setPosition(getPosition().getPosToWest());
                }else{
                    dir = Direction.SOUTH;
                    Position temp = getPosition();
                    setPosition(previousPosition);
                    setPreviousPosition(temp);
                }
                break;
            case WEST:
                if(maze.isMovable(getPosition().getPosToNorth())){
                    dir = Direction.NORTH;
                    setPreviousPosition(getPosition());
                    setPosition(getPosition().getPosToNorth());
                }else if(maze.isMovable(getPosition().getPosToSouth())){
                    dir = Direction.SOUTH;
                    setPreviousPosition(getPosition());
                    setPosition(getPosition().getPosToSouth());
                }else{
                    dir = Direction.EAST;
                    Position temp = getPosition();
                    setPosition(previousPosition);
                    setPreviousPosition(temp);
                }
                break;
            case SOUTH:
                if(maze.isMovable(getPosition().getPosToWest())){
                    dir = Direction.WEST;
                    setPreviousPosition(getPosition());
                    setPosition(getPosition().getPosToWest());
                }else if(maze.isMovable(getPosition().getPosToEast())){
                    dir = Direction.EAST;
                    setPreviousPosition(getPosition());
                    setPosition(getPosition().getPosToEast());
                }else{
                    dir = Direction.NORTH;
                    Position temp = getPosition();
                    setPosition(previousPosition);
                    setPreviousPosition(temp);
                }
                break;
            case EAST:
                if(maze.isMovable(getPosition().getPosToSouth())){
                    dir = Direction.SOUTH;
                    setPreviousPosition(getPosition());
                    setPosition(getPosition().getPosToSouth());
                }else if(maze.isMovable(getPosition().getPosToNorth())){
                    dir = Direction.NORTH;
                    setPreviousPosition(getPosition());
                    setPosition(getPosition().getPosToNorth());
                }else{
                    dir = Direction.WEST;
                    Position temp = getPosition();
                    setPosition(previousPosition);
                    setPreviousPosition(temp);
                }
                break;
        }
    }

    /**
     * Method that initializes which direction the robot
     * is going to walk. Also used when finding a new direction
     * when a move isn't possible.
     */
    private void initializeDirection(){

        if(maze.isMovable(this.getPosition().getPosToSouth()) &&
                !maze.isMovable(this.getPosition().getPosToWest())){
            dir = Direction.SOUTH;
        }else if(maze.isMovable(this.getPosition().getPosToEast()) &&
                !maze.isMovable(this.getPosition().getPosToSouth())){
            dir = Direction.EAST;
        }else if(maze.isMovable(this.getPosition().getPosToNorth()) &&
                !maze.isMovable(this.getPosition().getPosToEast())){
            dir = Direction.NORTH;
        }else if(maze.isMovable(this.getPosition().getPosToWest()) &&
                !maze.isMovable(this.getPosition().getPosToNorth())) {
            dir = Direction.WEST;
        }else{
            throw new IllegalArgumentException("No wall at start position.");
        }
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
}
