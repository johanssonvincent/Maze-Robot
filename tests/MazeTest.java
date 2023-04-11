/**
 *  @author  Vincent Johansson
 *  @since   2022-05-27
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Scanner;

public class MazeTest {

    @Test
    public void testConstructor() throws IOException{
        Maze testMaze = createTestMaze("maze1.txt");
        assertNotNull(testMaze);
    }

    @Test
    public void testGetStart() throws IOException, IllegalStateException{
        Maze testMaze = createTestMaze("maze1.txt");
        Position startPos = testMaze.getStart();
        assertNotNull(startPos);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                                        {createTestMaze("mazenostart.txt");});
        assertEquals("Maze doesn't have a start position.", thrown.getMessage());
    }

    @Test
    public void testHasGoal() throws IllegalStateException{
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                                        {Maze testMaze = createTestMaze("nogoalmaze.txt");});
        assertEquals("Maze doesn't have a goal position.", thrown.getMessage());
    }

    @Test
    public void testIsMovable() throws IOException{
        Maze testMaze = createTestMaze("maze1.txt");

        // Check positions known to be movable or not.
        assertFalse(testMaze.isMovable(testMaze.getStart().getPosToWest()));
        assertTrue(testMaze.isMovable(testMaze.getStart().getPosToSouth()));
    }

    @Test
    public void testGetNumRows() throws IOException{
        int knownRowValue = 7;
        Maze testMaze = createTestMaze("maze1.txt");
        assertEquals(knownRowValue, testMaze.getNumRows());
    }

    @Test
    public void testGetNumColumns() throws IOException{
        int knownColValue = 12;
        Maze testMaze = createTestMaze("maze1.txt");
        assertEquals(knownColValue, testMaze.getNumColumns());
    }

    @Test
    public void testIsGoal() throws IOException{
        Maze testMaze = createTestMaze("goaltestmaze.txt");

        // Check positions known to be goal or not.
        assertFalse(testMaze.isGoal(testMaze.getStart()));
        assertTrue(testMaze.isGoal(testMaze.getStart().getPosToSouth()));
    }

    private Maze createTestMaze(String mazeFile) throws IOException{
        File text = new File(mazeFile);
        Scanner scanner = new Scanner(text);
        return new Maze(scanner);
    }
}
