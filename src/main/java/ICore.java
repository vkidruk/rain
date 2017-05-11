package main.java;

public interface ICore {

    /**
     * To solve the Rain problem method
     * <p>
     * 
     * @param  int[] walls     - set of walls, where array length is the quantity of walls
     *                           and the value at each index is the height of the wall
     * @param  int[] rainRange - range of indexes where it rains
     * @return int quantity    - Quantity of water accumulated in puddles between walls
     */
	public int solve(int[] walls, int[] rainRange);

}
