// A* solver for the given Board configuration

import java.awt.*;
import java.util.*;
import java.util.List;

public class AStarSolver {
    private static final int SIZE = 3; // size of the board (3x3)
    private static final int[] MOVE_X = {-1, 1, 0, 0}; // horizontal movement
    private static final int[] MOVE_Y = {0, 0, -1, 1}; // vertical movement
    private static final int DIRECTIONS_COUNT = 4; // possible max moves.
    private static final String MOVE_DIRECTIONS = "UDLR"; // direction string to kee track valid solution path

    // inner class to represent every possible Board configuration
    static class State implements Comparable<State> {
        private int[][] board;
        private int x;
        private int y;
        private int moves;
        private String path;

        // Constructor for the State class
        State(int[][] board, int x, int y, int moves, String path) {
            this.board = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                this.board[i] = Arrays.copyOf(board[i], SIZE);
            }
            this.x = x;
            this.y = y;
            this.moves = moves;
            this.path = path;
        }

        // method to returns a list of all valid states from this state.
        List<State> getSuccessors() {
            List<State> successors = new ArrayList<>();
            for (int i = 0; i < DIRECTIONS_COUNT; i++) {
                int nx = x + MOVE_X[i];
                int ny = y + MOVE_Y[i];
                if (isValid(nx, ny)) {
                    State newState = new State(board, nx, ny, moves + 1, path + MOVE_DIRECTIONS.charAt(i));
                    swap(newState.board, x, y, nx, ny);
                    successors.add(newState);
                }
            }
            return successors;
        }

        // cost of this state
        int cost() {
            return moves + manhattan(board);
        }

        // equals override to compare two states.
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            State other = (State) obj;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (this.board[i][j] != other.board[i][j])
                        return false;
                }
            }
            return true;
        }

        // hashcode override to generate unique hash code for the state config.
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

        // override method to compare State,
        // in this way we don't need to call method "cost" every time
        @Override
        public int compareTo(State o) {
            return Integer.compare(this.cost(), o.cost());
        }
    }

    // method to implement A* algorithm,
    // take a Board object and generate a State from that Board config.
    // use helper method to solve problem.
    public static String solve(Board board) {
        int[][] initialStateArray = board.getCurrentBoardState();
        PriorityQueue<State> frontier = new PriorityQueue<>();
        Set<State> explored = new HashSet<>();

        Point emptyCellPosition = findInitialEmptyCellPosition(initialStateArray);
        State initialState = new State(initialStateArray, emptyCellPosition.x, emptyCellPosition.y, 0, "");
        frontier.add(initialState);

        while (!frontier.isEmpty()) {
            State currentState = frontier.poll();
            if (isGoal(currentState.board)) {
                return currentState.path;
            }
            if (!explored.contains(currentState)) {
                explored.add(currentState);
                exploreSuccessors(currentState, frontier, explored);
            }
        }
        return null;
    }

    private static Point findInitialEmptyCellPosition(int[][] initialStateArray) {
        for (int i = 0; i < initialStateArray.length; i++) {
            for (int j = 0; j < initialStateArray[i].length; j++) {
                if (initialStateArray[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private static void exploreSuccessors(State currentState, PriorityQueue<State> frontier, Set<State> explored) {
        for (State successor : currentState.getSuccessors()) {
            if (!explored.contains(successor) && !frontier.contains(successor)) {
                frontier.add(successor);
            }
        }
    }

    // check that given array is the goal board configuration or not.
    private static boolean isGoal(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0 && board[i][j] != i * SIZE + j + 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // method to check whether move is valid or not.
    private static boolean isValid(int nx, int ny) {
        return nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE;
    }

    // make swap on the given board using array representation of that board.
    private static void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    // Calculate the manhattan distance for the given board config.
    private static int manhattan(int[][] board) {
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


}
