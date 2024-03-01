import java.awt.*;

// A program that partially implements the 8 puzzle.
public class EightPuzzle {
    // The main method is the entry point where the program starts execution.
    public static void main(String[] args) {
        // StdDraw setup
        // -----------------------------------------------------------------------
        // set the size of the canvas (the drawing area) in pixels
        StdDraw.setCanvasSize(800, 800);
        // set the range of both x and y values for the drawing canvas
        StdDraw.setScale(0.5, 3.5);
        // enable double buffering to animate moving the tiles on the board
        StdDraw.enableDoubleBuffering();

        // create a random board for the 8 puzzle
        Board board = new Board();
        String solutionPath = AStarSolver.solve(board);
        //   System.out.println(solutionPath);


        for (int i = 0; i < solutionPath.length(); i++) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(1.90, 2, " Move: " + solutionPath.charAt(i));
            StdDraw.show();
            StdDraw.pause(750);

            switch (solutionPath.charAt(i)) {
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
            board.draw();
            StdDraw.show();
            StdDraw.pause(750);
        }
        StdDraw.clear(StdDraw.BLUE);
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(1.90, 2, " Solution: " + solutionPath);
        StdDraw.show();

    }
}