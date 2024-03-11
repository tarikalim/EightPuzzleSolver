
public class HeuristicFunctions {
    private static final int SIZE = 3; // size of the board. Usage in 2d array: [SIZE]X[SIZE]

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

    public static int misplacedTiles(int[][] board) {
        int h1 = 0;
        int[][] goal = {{1, 2, 3,},
                {4, 5, 6},
                {7, 8, 0}
        };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != goal[i][j] && board[i][j] != 0) {
                    h1++;
                }
            }
        }


        return h1;
    }
}
