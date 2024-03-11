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

    private static String chosenHeuristic;
    private static final String INFO_TEXT = "Welcome to 8 Puzzle Problem\n" +
            "To solve puzzle with Manhattan Distance press '1'\n" +
            "To solve puzzle with Misplaced Tiles press '2'\n" +
            "After selecting one of them, you can see the puzzle\n" +
            "You can play with board using arrow keys\n" +
            "After you play, press 'ENTER' to see solution according to your heuristic function\n" +
            "Program will visualise the solution and after that you can see the exact solution path. ";
    public static void main(String[] args) {
        setupStdDraw();
        StdDraw.clear(BACKGROUND_COLOR);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(TEXT_FONT);
        drawMultilineText(INFO_TEXT, 2, 2.5, 0.1);
        StdDraw.show();

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
                chosenHeuristic = "manhattan";
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
                heuristic = HeuristicFunctions::misplacedTiles;
                chosenHeuristic = "Misplaced Tiles";
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

        // show complete solution path as a string after drawing solution
        StdDraw.clear(BACKGROUND_COLOR);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(TEXT_FONT);
        StdDraw.text(1.95, 2, "Solution with "+ chosenHeuristic+ " :" + solutionPath);
        StdDraw.show();
    }

    // method to set up Std draw configuration
    private static void setupStdDraw() {
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setScale(SCALE_MIN, SCALE_MAX);
        StdDraw.enableDoubleBuffering();
    }
    // method to split info text row by row
    // because Std draw.text doesn't support this functionality.
    public static void drawMultilineText(String text, double x, double y, double lineHeight) {
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            StdDraw.text(x, y - i * lineHeight, lines[i]);
        }
    }



}
