// A program that partially implements the 8 puzzle.
public class EightPuzzle {
    // The main method is the entry point where the program starts execution.
    public static void main(String[] args) {
        // StdDraw setup
        // -----------------------------------------------------------------------
        // set the size of the canvas (the drawing area) in pixels
        StdDraw.setCanvasSize(500, 500);
        // set the range of both x and y values for the drawing canvas
        StdDraw.setScale(0.5, 3.5);
        // enable double buffering to animate moving the tiles on the board
        StdDraw.enableDoubleBuffering();

        // create a random board for the 8 puzzle
        Board board = new Board();
        AStarSolver solver = new AStarSolver();
        String solutionPath = solver.solve(board);

        for (int i = 0; i < solutionPath.length(); i++) {
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
            StdDraw.pause(500); // H


        }
    }
}