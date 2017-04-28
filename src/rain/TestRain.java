package rain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRain {

    @Test
    public void testRun() {
        Core core = new Core();
        int[] walls = new int[] {2, 5, 1, 2, 3, 4, 7, 7, 6};
        int[] rainRange = new int[] {0, 8};

        int actual = core.solve(walls, rainRange);

        assertEquals("run(): expected result 10", 10, actual);
    }

    @Test
    public void testTopIndex() {
        Core core = new Core();
        int[] walls = new int[] {2, 5, 1, 2, 3, 4, 7, 7, 6};
        int[] rainRange = new int[] {0, 8};

        int actual = core.getHighestWallIndex(walls, rainRange);

        assertEquals("getTopIndex(): expected result 6", 6, actual);
    }
}
