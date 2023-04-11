/**
 *  @author  Vincent Johansson
 *  @since   2022-05-27
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Scanner;

public class RHRRTest {

    @Test
    public void testConstructor() throws IOException {
        RightHandRuleRobot testRobot = createTestRobot("maze1.txt");
        assertNotNull(testRobot);
    }

    @Test
    public void testGetPosition() throws IOException {
        File text = new File("maze1.txt");
        Scanner scanner = new Scanner(text);
        Maze testMaze = new Maze(scanner);
        RightHandRuleRobot testRobot = new RightHandRuleRobot(testMaze);

        assertEquals(testMaze.getStart(), testRobot.getPosition());
        testRobot.move();
        assertEquals(testMaze.getStart().getPosToSouth(), testRobot.getPosition());
    }

    @Test
    public void testMove() throws IOException {
        int stepCount = 0;
        RightHandRuleRobot testRobot = createTestRobot("maze1.txt");
        while(!testRobot.hasReachedGoal()){
            testRobot.move();
            stepCount++;
        }
        assertTrue(stepCount > 0);
    }

    @Test
    public void testHasReachedGoal() throws IOException {
        RightHandRuleRobot testRobot = createTestRobot("goaltestmaze.txt");

        assertFalse(testRobot.hasReachedGoal());
        testRobot.move();
        assertTrue(testRobot.hasReachedGoal());
    }

    private RightHandRuleRobot createTestRobot(String mazeFile) throws IOException {
        File text = new File(mazeFile);
        Scanner scanner = new Scanner(text);
        Maze testMaze = new Maze(scanner);
        return new RightHandRuleRobot(testMaze);
    }
}
