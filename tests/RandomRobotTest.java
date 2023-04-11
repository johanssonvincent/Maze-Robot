/**
 *  @author  Vincent Johansson
 *  @since   2022-05-24
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Scanner;

public class RandomRobotTest {

    @Test
    public void testConstructor() throws IOException {
        RandomRobot testRobot = createTestRobot("maze1.txt");
        assertNotNull(testRobot);
    }

    @Test
    public void testGetPosition() throws IOException {
        File text = new File("maze1.txt");
        Scanner scanner = new Scanner(text);
        Maze testMaze = new Maze(scanner);
        RandomRobot testRobot = new RandomRobot(testMaze);

        assertEquals(testMaze.getStart(), testRobot.getPosition());
        testRobot.move();
        assertEquals(testMaze.getStart().getPosToSouth(), testRobot.getPosition());
    }

    @Test
    public void testHasReachedGoal() throws IOException {
        RandomRobot testRobot = createTestRobot("goaltestmaze.txt");

        assertFalse(testRobot.hasReachedGoal());
        testRobot.move();
        assertTrue(testRobot.hasReachedGoal());
    }

    @Test
    public void testMove() throws IOException {
        int stepCount = 0;
        RandomRobot testRobot = createTestRobot("maze1.txt");
        while(!testRobot.hasReachedGoal()){
            testRobot.move();
            stepCount++;
        }
        assertTrue(stepCount > 0);
    }

    private RandomRobot createTestRobot(String mazeFile) throws IOException {
        File text = new File(mazeFile);
        Scanner scanner = new Scanner(text);
        Maze testMaze = new Maze(scanner);
        return new RandomRobot(testMaze);
    }
}
