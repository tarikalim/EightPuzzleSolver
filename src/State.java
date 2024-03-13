import java.util.Arrays;
import java.util.function.Function;

/**
 * Represents the state of a puzzle board in the A* search algorithm.
 * Stores the board configuration, position of the empty cell, number of moves made, and the path taken so far.
 */
public class State implements Comparable<State> {
    private static final int SIZE = 3; // Size of the board. Usage in 2d array: [SIZE]X[SIZE]

    private int[][] board;
    private int x;
    private int y;
    private int moves;
    private String path;
    private int cost; // Total cost of reacihng this state, moves + value calculated by selected heuristic.

    /**
     * Function to calculate the heuristic value for a given board configuration.
     * Default implementation returns 0, meaning it needs to be overridden for actual heuristic calculation.
     */
    public static Function<int[][], Integer> heuristicFunction = board -> 0;

    /**
     * Constructs a new State with the specified board configuration and details.
     *
     * @param board The current board configuration.
     * @param x     The x-coordinate (row) of the empty cell.
     * @param y     The y-coordinate (column) of the empty cell.
     * @param moves The number of moves made from the initial state to this state.
     * @param path  The path of moves made from the initial state to this state.
     */

    public State(int[][] board, int x, int y, int moves, String path) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            this.board[i] = Arrays.copyOf(board[i], SIZE);
        }
        this.x = x;
        this.y = y;
        this.moves = moves;
        this.path = path;
        this.cost = calculateCost();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMoves() {
        return moves;
    }

    public String getPath() {
        return path;
    }

    public int getCost() {
        return cost;
    }

    /**
     * Calculates the total cost of reaching the current state.
     * Combines the number of moves made with the heuristic value for the board configuration.
     *
     * @return The total cost of the state.
     */
    public int calculateCost() {
        return moves + heuristicFunction.apply(board);
    }

    /**
     * Calculates the total cost of reaching the current state.
     * Combines the number of moves made with the heuristic value for the board configuration.
     *
     * @return The total cost of the state.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State other = (State) obj;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.board[i][j] != other.board[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * Generates a hash code for the state based on its board configuration.
     *
     * @return The hash code of the state.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                hash = 31 * hash + board[i][j];
            }
        }
        return hash;
    }

    /**
     * Compares this state with another state to determine their priority order.
     *
     * @param o The other state to compare with.
     * @return A negative integer, zero, or a positive integer as this state is less than,
     * equal to, or greater than the specified state.
     */
    @Override
    public int compareTo(State o) {
        return Integer.compare(this.calculateCost(), o.getCost());
    }
}