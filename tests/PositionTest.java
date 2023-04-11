/**
 * @author  Vincent Johansson
 * @since   2022-05-27
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testConstructor(){
        Position pos = new Position(1,1);
        assertNotNull(pos);
    }

    @Test
    public void testGetX(){
        Position pos = new Position(1,1);
        assertEquals(1, pos.getX());
    }

    @Test
    public void testGetY(){
        Position pos = new Position(1,1);
        assertEquals(1, pos.getY());
    }

    @Test
    public void testGetPosSouth(){
        Position pos = new Position(1,1);
        Position expectedPos = new Position(2, 1);
        assertEquals(pos.getPosToSouth(), expectedPos);
    }

    @Test
    public void testGetPosNorth(){
        Position pos = new Position(1,1);
        Position expectedPos = new Position(0, 1);
        assertEquals(pos.getPosToNorth(), expectedPos);
    }

    @Test
    public void testGetPosWest(){
        Position pos = new Position(1,1);
        Position expectedPos = new Position(1, 0);
        assertEquals(pos.getPosToWest(), expectedPos);
    }

    @Test
    public void testGetPosEast(){
        Position pos = new Position(1,1);
        Position expectedPos = new Position(1, 2);
        assertEquals(pos.getPosToEast(), expectedPos);
    }

    @Test
    public void testEquals(){
        Position pos = new Position(1,1);
        Position pos2 = new Position(1,1);

        assertTrue(pos.equals(pos2));
    }
}
