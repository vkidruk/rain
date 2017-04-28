package rain;

/**
 * @author Valentyn Kidruk
 */
public class Core {

    /**
     * To solve the Rain problem method
     * <p>
     * 
     * The algorithm short description:
     * 1. Fill water inside rain range
     * 2. If during that ground found then stop and exit
     * 3. Find nearest bigger wall and extend rainRange
     * 4. Repeat from step 1
     *
     * @param  int[] walls     - set of walls, where array length is the quantity of walls
     *                           and the value at each index is the height of the wall
     * @param  int[] rainRange - range of indexes where it rains
     * @return int quantity    - Quantity of water accumulated in puddles between walls
     */
    public int solve(int[] walls, int[] rainRange) {

        water = new int[walls.length];

        int leftIndex, rightIndex;
        boolean leftGround, rightGround;
        boolean groundFound = false;

        while (!groundFound) {
            for (int i = rainRange[0]; i <= rainRange[1]; i = rightIndex) {
                leftIndex = fill(walls, i, Direction.LEFT);
                rightIndex = fill(walls, i, Direction.RIGHT);

                leftGround = (leftIndex < 0) || (walls[leftIndex] == 0);
                rightGround = (rightIndex >= walls.length) || (walls[rightIndex] == 0);
                groundFound |= leftGround || rightGround;
            }

            if (!groundFound) {
                int nearLeftTopIndex = getNearestWallIndex(walls, rainRange[0], Direction.LEFT);
                int nearRightTopIndex = getNearestWallIndex(walls, rainRange[1], Direction.RIGHT);
                if (walls[nearLeftTopIndex] <= walls[nearRightTopIndex]) {
                    rainRange[0] = nearLeftTopIndex;
                } else {
                    rainRange[1] = nearRightTopIndex;
                }
            }
        }

        int quantity = 0;
        for (int item: water) {
        	quantity += item;
        }
        
        return quantity;
    }

    int[] getWater() {
        return water;
    }

    /**
     * To fill walls by water
     * <p>
     * 
     * Fill walls by water from specified point and using specified direction
     * 
     * @param  int[] walls         - set of walls, where array length is the quantity of walls
     *                               and the value at each index is the height of the wall
     * @param  int wallIndex       - index of start point
     * @param  Direction direction - one of 2 possible direction: LEFT or RIGHT
     * @return index of stop point
     */
    private int fill(int[] walls, int wallIndex, Direction direction) {
        int dir = direction.getValue();
        int index = wallIndex + dir;
        while ((dir == 1 ? index < walls.length : index >= 0) && walls[index] != 0) {
            if (walls[index] > walls[index - dir]) {
                for (int height = walls[index - dir] + 1;
                		 height <= walls[index] && height <= walls[wallIndex]; height++) {
                    for (int idx = index - dir; walls[idx] < height; idx -= dir) {
                        if (water[idx] < height - walls[idx]) {
                            water[idx] = height - walls[idx];
                        }
                    }
                }
            }

            if (walls[index] > walls[wallIndex])
                break;

            index += dir;
        }
        return index;
    }

    /**
     * To find near wall
     * <p>
     * 
     * Find wall near specified point using specified direction
     * 
     * @param  int[] walls         - set of walls, where array length is the quantity of walls
     *                               and the value at each index is the height of the wall
     * @param  int startIndex      - index of start point
     * @param  Direction direction - one of 2 possible direction: LEFT or RIGHT
     * @return index of found wall
     */
    private int getNearestWallIndex(int[] walls, int startIndex, Direction direction) {
        int dir = direction.getValue();
        int index = startIndex + dir;
        while ((dir == 1 ? index < walls.length : index >= 0)
        		&& walls[index] <= walls[startIndex]) {
            index += dir;
        }
        while ((dir == 1 ? index < walls.length : index >= 0)
        		&& walls[index] > walls[index - dir]) {
            index += dir;
        }
        return index - dir;
    }

    /**
     * To find highest wall
     * <p>
     * 
     * Find highest wall using specified range
     * 
     * @param  int[] walls     - set of walls, where array length is the quantity of walls
     *                           and the value at each index is the height of the wall
     * @param  int[] rainRange - range of indexes where it rains
     * @return index of found wall
     */
    public int getHighestWallIndex(int[] walls, int[] rainRange) {
        int top = -1;
        int index = -1;
        for (int i = rainRange[0]; i <= rainRange[1]; i++) {
            if (top == -1 || top < walls[i]) {
                top = walls[index = i];
            }
        }
        return index;
    }

    /**
     * set of "water walls", where array length is the quantity of walls
     * and the value at each index is the height of the water over wall
     */
    private int[] water;
}
