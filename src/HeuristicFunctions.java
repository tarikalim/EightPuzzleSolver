// heuristic function class, we can add any heuristic with using this class
// to set method cost to use our algorithm.
public class HeuristicFunctions {
    private static final int SIZE = 3; // size of the board. Usage in 2d array: [SIZE]X[SIZE]
    private static final int[][] GOAL = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };

    /**
     * Calculates the Manhattan Distance heuristic for the 8-puzzle problem.
     * This heuristic calculates the total distance each tile is from its goal position,
     * where distance is the sum of the absolute differences in the tile's x and y coordinates.
     *
     * @param board The current state of the puzzle board.
     * @return The total Manhattan Distance for all tiles.
     */
    public static int manhattan(int[][] board) {
        int distance = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int value = board[i][j];
                if (value != 0) {
                    int targetX = (value - 1) / SIZE;
                    int targetY = (value - 1) % SIZE;
                    distance += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return distance;
    }

    /**
     * Calculates the Misplaced Tiles heuristic for the 8-puzzle problem.
     * This heuristic counts the number of tiles that are in the wrong position compared to the goal state.
     *
     * @param board The current state of the puzzle board.
     * @return The number of misplaced tiles.
     */

    public static int misplacedTiles(int[][] board) {
        int misplaced = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0 && board[i][j] != GOAL[i][j]) {
                    misplaced++;
                }
            }
        }
        return misplaced;
    }
}
