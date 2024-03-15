// Main algorithm class. Detailed explanation of every method and parameter are given.

import java.awt.*;
import java.util.*;
import java.util.List;

public class AStarSolver {
    /**
     * Size of the board.
     * Usage in 2d array: [SIZE]X[SIZE]
     * Since 2D representation of arrays in computers different from normal math, we need to understand that.
     * First version of board
     * 0,0 | 0,1 | 0,2
     * ----------------
     * 1,0 |Empty| 1,2
     * ----------------
     * 2,0 | 2,1 | 2,2
     * If we want to move empty cell up, then we need to change 1,1 to 0,1
     * so X = -1 and Y= 0 means that move up.
     * Because 2d array logic is different from normal math,
     * understanding this structure is important.
     */
    private static final int SIZE = 3;
    private static final int[] MOVE_X = {-1, 1, 0, 0}; // horizontal movement
    private static final int[] MOVE_Y = {0, 0, -1, 1}; // vertical movement
    private static final int DIRECTIONS_COUNT = 4; // possible max moves.
    private static final String MOVE_DIRECTIONS = "UDLR"; // possible directions according to X and Y changes

    /**
     * Solves the given Eight Puzzle boards using the A* search algorithm.
     * It starts from the initial board state,
     * exploring possible moves until it finds the solution that leads to the goal state.
     * It employs a priority queue
     * to manage the exploration of states in a cost-effective manner,
     * ensuring that the most promising paths are explored first.
     * Explored states are tracked to avoid revisiting them, improving the efficiency of the search.
     * The method returns
     * the sequence of moves required to solve the puzzle if a solution exists,
     * or null if the puzzle is unsolvable.
     *
     * @param board The initial state of the Eight Puzzle boards.
     * @return A string representing the sequence of moves to solve the puzzle, or null if no solution is found.
     * But in our case, since we don't let the program generate unsolvable board configuration,
     * method will always return a path.
     * Implementation of generating only solvable puzzle is explained in "Board" class.
     */
    public static String solve(Board board) {
        int[][] initialStateArray = board.getCurrentBoard_Array();
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

    /**
     * Searches the initial state of the puzzle board for the position of the empty cell (represented by 0).
     * This method scans
     * the board row by row and column by column to locate the empty cell,
     * which is necessary for determining possible moves
     * and generating successor states.
     * The position of the empty cell is crucial for the puzzle-solving process, as it dictates
     * which tiles can be moved.
     * Understanding the initial position of the empty cell helps in planning the strategy to solve the puzzle.
     *
     * @param initialStateArray The current configuration of the puzzle board, represented as a 2D array of integers.
     *                          Each element corresponds to a tile's value, with 0 representing the empty cell.
     * @return A Point object containing the coordinates (row and column) of the empty cell on the board.
     * If no empty cell is found, returns null.
     * The presence of the empty cell is a prerequisite for a valid puzzle state.
     */
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


    /**
     * Calculates and returns all valid successor states from the current state.
     * Each valid move generates a new state by copying the current state
     * and then moving the empty cell to a new position.
     * For each generated successor, the number of moves is incremented by one, and the direction of the move
     * is appended to a path string.
     * The algorithm can then explore these successor states in later steps.
     *
     * @param currentState The current state represented by a State object.
     * @return A list of successor states.
     * Each State object represents a possible state achievable by making a single move from the current state.
     */
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

    /**
     * Manages the exploration of successor states for a given current state.
     * Uses the list of successors
     * generated by the getSuccessors method.
     * Each successor is checked against the already explored states and
     * the states currently in the priority queue.
     * If a successor state is found to be not already explored or
     * waiting in the priority queue, it is added to the priority queue for future exploration.
     * This approach
     * ensures that no state is explored more than once, improving the efficiency of the search process.
     * And also, if explored successor state is already in the priority queue, method will check which state's
     * cost is less, if the new explored has less cost than method will change the already existing state with
     * new explored successor state.
     * In this way, priority queue always keeps the states that have the least cost.
     * With this method, algorithm will always find the shortest path,
     * and it will also search minimal state to find the shortest path.
     *
     * @param currentState The current state being explored, from which successors will be generated.
     * @param frontier     The priority queue (frontier) where states are kept for exploration, ordered by their priority.
     * @param explored     A Set containing states that have already been explored to avoid revisiting them.
     */
    public static void exploreSuccessors(State currentState, PriorityQueue<State> frontier, Set<State> explored) {
        List<State> successors = getSuccessors(currentState);
        for (State successor : successors) {
            if (!explored.contains(successor)) {
                boolean isAdded = false;
                for (State existingStatePQ : new ArrayList<>(frontier)) {
                    if (existingStatePQ.equals(successor)) {
                        if (existingStatePQ.getCost() > successor.getCost()) {
                            frontier.remove(existingStatePQ);
                            frontier.add(successor);
                            isAdded = true;
                            break;
                        }
                    }
                }
                if (!isAdded && !frontier.contains(successor)) {

                    frontier.add(successor);
                }
            }
        }
    }


    /**
     * Checks if the given board configuration matches the goal configuration of the puzzle.
     * The goal is achieved when the tiles are in ascending order from left to right and top to bottom,
     * with the blank (zero) tile in the last position.
     * This method iterates over each tile of the board, comparing the value of each tile
     * against its expected value in the goal configuration.
     *
     * @param board The current configuration of the puzzle board, represented as a 2D array of integers.
     * @return true if the board matches the goal configuration; false otherwise.
     */
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

    /**
     * Determines whether a proposed move to a new (nx, ny) position is valid within the puzzle board.
     * A move is considered valid if it results in the empty tile moving to a position within the boundaries of the board.
     * This method checks if the new position is within the grid defined by SIZE, ensuring that the move does not go outside the board.
     *
     * @param nx The x-coordinate (row) of the new position to which the empty tile is proposed to move.
     * @param ny The y-coordinate (column) of the new position to which the empty tile is proposed to move.
     * @return true if the move is within the board's boundaries; false otherwise.
     */
    private static boolean isValid(int nx, int ny) {
        return nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE;
    }

    /**
     * Performs a swap operation on the puzzle board by exchanging the values at two specified positions.
     * This method directly manipulates the array representation of the board, reflecting a move made in the puzzle.
     * Swapping is essential for moving tiles around the board as part of generating successor states.
     * It is a fundamental operation for modifying the board state without creating a new board instance.
     *
     * @param board The puzzle board represented as a 2D integer array, where each element corresponds to a tile's value.
     * @param x1    The row index of the first position to be swapped.
     * @param y1    The column index of the first position to be swapped.
     * @param x2    The row index of the second position to be swapped.
     * @param y2    The column index of the second position to be swapped.
     */
    public static void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }


}
