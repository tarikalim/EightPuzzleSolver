import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Function;

public class EightPuzzle {
    // default parameters to use in class
    private static final int CANVAS_SIZE = 800;
    private static final double SCALE_MIN = 0.5, SCALE_MAX = 3.5;
    private static final int PAUSE_DURATION = 100;
    private static final Color BACKGROUND_COLOR = new Color(15, 76, 129);
    private static final Font TEXT_FONT = new Font("Arial", Font.BOLD, 20);

    public static void main(String[] args) {
        setupStdDraw();
        //  create main board object
        Board board = new Board();
        // copy first board to find solution,
        // we need this because after user play with board,
        // we need to use first state of the board to solve.
        Board copyBoard = new Board(board);
        // for making choice between manhattan and misplaced heuristic functions
        Function<int[][], Integer> heuristic = null;

        while (heuristic == null) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
                heuristic = HeuristicFunctions::manhattan;
                StdDraw.pause(PAUSE_DURATION);
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
                heuristic = HeuristicFunctions::misplacedTiles;
                StdDraw.pause(PAUSE_DURATION);
            }
        }
        // while loop to let user play on the board,
        // after user press enter, break the loop.
        while (true) {
            board.draw();
            StdDraw.show();
            userPlay(board);
            if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
                StdDraw.pause(PAUSE_DURATION);
                break;
            }
        }

        // after user press enter, break while loop and program will solve copy board and show the solution
        StdDraw.clear(BACKGROUND_COLOR);
        State.heuristicFunction = heuristic;
        String solutionPath = AStarSolver.solve(copyBoard); // use copy board to find solution
        assert solutionPath != null;
        showSolution(copyBoard, solutionPath); // show solution

    }


    // method to let user play on board
    private static void userPlay(Board board) {
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            board.moveRight();
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            board.moveLeft();
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
            board.moveUp();
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
            board.moveDown();
        }
        StdDraw.pause(PAUSE_DURATION);
    }

    // method to show solution on the board
    private static void showSolution(Board board, String solutionPath) {
        // for loop to visualize solution using Board class move methods
        // and solutionPath string to manage move methods.
        for (char move : solutionPath.toCharArray()) {
            switch (move) {
                case 'U':
                    board.moveUp();
                    break;
                case 'D':
                    board.moveDown();
                    break;
                case 'L':
                    board.moveLeft();
                    break;
                case 'R':
                    board.moveRight();
                    break;
            }
            // after each solution step showed on the board, show step as a string on the screen
            // and draw the board using Board classes draw func.
            board.draw();
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setFont(TEXT_FONT);
            StdDraw.text(1.97, 1.80, "Move: " + move);
            StdDraw.show();
            StdDraw.pause(750);
        }

        // show solution path as a string after drawing solution
        StdDraw.clear(BACKGROUND_COLOR);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(TEXT_FONT);
        StdDraw.text(1.95, 2, "Solution: " + solutionPath);
        StdDraw.show();
    }

    // method to set up Std draw configuration
    private static void setupStdDraw() {
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setScale(SCALE_MIN, SCALE_MAX);
        StdDraw.enableDoubleBuffering();
    }


}
