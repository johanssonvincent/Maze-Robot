/**
 * @author  Vincent Johansson
 * @since   2022-05-27
 */

import java.io.IOException;
import java.util.ArrayList;

/**
 *  Maze Class
 *  Handles all the functionality of the
 *  maze that the robot is traversing.
 */
public class Maze {

    /* ArrayList to store maze layout for easy access. */
    private final ArrayList<String> mazeLayout = new ArrayList<>();

    /**
     *  Constructor method for Maze class.
     *
     *  @param   inFile      .txt file containing given maze
     *  @throws  IllegalStateException  IllegalStateException
     *  @throws  IOException    IOException
     */
    public Maze(java.util.Scanner inFile) throws IllegalStateException, IOException {
        readMaze(inFile);
        getStart();
        hasGoal();
    }

    /**
     *  Method that reads given .txt file and parses it to the string ArrayList.
     *
     *  @param   inFile      .txt file containing given maze
     */
    public void readMaze(java.util.Scanner inFile){
        while(inFile.hasNextLine()){
            this.mazeLayout.add(inFile.nextLine());
        }
    }

    /**
     *  Method that checks if it's possible to move to a given position.
     *
     *  @param   position    Position object
     *  @return  boolean     true/false
     */
    public boolean isMovable(Position position){
        try{
            /* If X or Y coordinate is outside the maze, return false. */
            if(position.getX() < 0 || position.getY() < 0){
                return false;
            }

            if(position.getX() > getNumColumns() || position.getY() > getNumRows()-1){
                return false;
            }

            return mazeLayout.get(position.getY()).charAt(position.getX()) != '*';
        }catch(StringIndexOutOfBoundsException e){
            return false;
        }
    }

    /**
     *  Method that checks if given position is a goal position.
     *
     *  @param   position    Position object
     *  @return  boolean     true/false
     */
    public boolean isGoal(Position position){
        return mazeLayout.get(position.getY()).charAt(position.getX()) == 'G';
    }

    /**
     *  Method that checks if given maze
     *  has any goal positions.
     *
     *  @throws   IllegalStateException thrown if maze doesn't have a goal position
     */
    public void hasGoal() throws IllegalStateException{
        for (String s : this.mazeLayout)
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'G') {
                    return;
                }
            }
        throw new IllegalStateException("Maze doesn't have a goal position.");
    }

    /**
     *  Method that finds start position in given maze
     *  and returns it.
     *
     *  @return  Position object
     *  @throws   IllegalStateException thrown if maze doesn't have a start position
     */
    public Position getStart() throws IllegalStateException{
        for(int i = 0; i < this.mazeLayout.size(); i++){
            for(int j = 0; j < this.mazeLayout.get(i).length(); j++){
                if(this.mazeLayout.get(i).charAt(j) == 'S'){
                    return new Position(i,j);
                }
            }
        }
        throw new IllegalStateException("Maze doesn't have a start position.");
    }

    /**
     *  Method that returns int value
     *  containing amount of columns in the current maze.
     *
     *  @return  col     Integer, number of columns.
     */
    public int getNumColumns(){

        int col = 0;

        for(int i = 0; i < getNumRows(); i++){
            if(this.mazeLayout.get(i).length() > col){
                col = this.mazeLayout.get(i).length();
            }
        }

        return col;
    }

    /**
     *  Method that returns int value
     *  containing amount of rows in the current maze.
     *
     *  @return int Number of rows.
     */
    public int getNumRows(){ return this.mazeLayout.size(); }
}
