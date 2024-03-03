import java.awt.*;
import java.util.*;
import java.util.List;

public class AStarSolver {
    public static final int SIZE = 3; // size of the board. Usage in 2d array: [SIZE]X[SIZE]
    // since 2D representation of arrays in computers different then normal math, we need to understand that.
// first version of board
//     0,0 | 0,1 | 0,2
//     ----------------
//     1,0 | Empty| 1,2
//     ----------------
//     2,0 | 2,1 | 2,2
// if we want to move empty cell up than we need to change 1,1 to 0,1
// so X = -1 and Y= 0 means that move up. because 2d array logic is different than normal math,
// understanding this structure is important

    private static final int[] MOVE_X = {-1, 1, 0, 0}; // horizontal movement
    private static final int[] MOVE_Y = {0, 0, -1, 1}; // vertical movement
    private static final int DIRECTIONS_COUNT = 4; // possible max moves.
    private static final String MOVE_DIRECTIONS = "UDLR"; // possible directions according to X and Y changes

    // method to implement A* algorithm,
    // take a Board object and generate a State from that Board config.
    // use helper method to solve problem.
    public static String solve(Board board) {
        int[][] initialStateArray = board.getCurrentBoardState();
        PriorityQueue<State> frontier = new PriorityQueue<>();
        Set<State> explored = new HashSet<>();

        Point emptyCellPosition = findInitialEmptyCellPosition(initialStateArray);
        assert emptyCellPosition != null;
        State initialState = new State(initialStateArray, emptyCellPosition.x, emptyCellPosition.y, 0, "");
        frontier.add(initialState);

        while (!frontier.isEmpty()) {
            State currentState = frontier.poll();
            if (isGoal(currentState.getBoard())) {
                return currentState.getPath();
            }
            if (!explored.contains(currentState)) {
                explored.add(currentState);
                exploreSuccessors(currentState, frontier, explored);
            }
        }
        return null;
    }

    public static Point findInitialEmptyCellPosition(int[][] initialStateArray) {
        for (int i = 0; i < initialStateArray.length; i++) {
            for (int j = 0; j < initialStateArray[i].length; j++) {
                if (initialStateArray[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }


    public static List<State> getSuccessors(State currentState) {
        List<State> successors = new ArrayList<>();
        for (int i = 0; i < DIRECTIONS_COUNT; i++) {
            int nx = currentState.getX() + MOVE_X[i];
            int ny = currentState.getY() + MOVE_Y[i];
            if (isValid(nx, ny)) {
                State newState = new State(currentState.getBoard(), nx, ny, currentState.getMoves() + 1, currentState.getPath() + MOVE_DIRECTIONS.charAt(i));
                swap(newState.getBoard(), currentState.getX(), currentState.getY(), nx, ny);
                successors.add(newState);
            }
        }
        return successors;
    }

    public static void exploreSuccessors(State currentState, PriorityQueue<State> frontier, Set<State> explored) {
        List<State> successors = getSuccessors(currentState);
        for (State successor : successors) {
            if (!explored.contains(successor) && !frontier.contains(successor)) {
                frontier.add(successor);
            }
        }
    }


    // check that given array is the goal board configuration or not.
    public static boolean isGoal(int[][] board) {
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
    public static void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    // Calculate the manhattan distance for the given board config.
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


}
