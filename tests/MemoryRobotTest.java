/**
 *  @author  Vincent Johansson
 *  @since   2022-05-27
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Scanner;

public class MemoryRobotTest {

    @Test
    public void testConstructor() throws IOException {
        MemoryRobot testRobot = createTestRobot("maze1.txt");
        assertNotNull(testRobot);
    }

    @Test
    public void testGetPosition() throws IOException {
        File text = new File("maze1.txt");
        Scanner scanner = new Scanner(text);
        Maze testMaze = new Maze(scanner);
        MemoryRobot testRobot = new MemoryRobot(testMaze);

        assertEquals(testMaze.getStart(), testRobot.getPosition());
        testRobot.move();
        assertEquals(testMaze.getStart().getPosToSouth(), testRobot.getPosition());
    }

    @Test
    public void testHasReachedGoal() throws IOException {
        MemoryRobot testRobot = createTestRobot("goaltestmaze.txt");

        assertFalse(testRobot.hasReachedGoal());
        testRobot.move();
        assertTrue(testRobot.hasReachedGoal());
    }

    @Test
    public void testMove() throws IOException {
        int stepCount = 0;
        MemoryRobot testRobot = createTestRobot("maze1.txt");
        while(!testRobot.hasReachedGoal()){
            testRobot.move();
            stepCount++;
        }
        assertTrue(stepCount > 0);
    }

    @Test
    public void confirmSteps() throws IOException {
        int correctSteps = 45;
        int stepCounter = 0;

        MemoryRobot testRobot = createTestRobot("maze1.txt");

        while (!testRobot.hasReachedGoal()) {
            testRobot.move();
            stepCounter++;
        }

        assertEquals(correctSteps, stepCounter);
    }

    private MemoryRobot createTestRobot(String mazeFile) throws IOException {
        File text = new File(mazeFile);
        Scanner scanner = new Scanner(text);
        Maze testMaze = new Maze(scanner);
        return new MemoryRobot(testMaze);
    }
}
