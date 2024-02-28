import java.awt.Color; // used for coloring the board
import java.awt.Point; // used for the positions of the tiles and the empty cell
import java.util.Objects;


// A class that is used for modeling the board in the 8 puzzle.
public class Board {
    // Data fields: the class variables (actually constants here)
    // --------------------------------------------------------------------------
    // the background color used for the empty cell on the board
    private static final Color backgroundColor = new Color(145, 234, 255);
    // the color used for drawing the boundaries around the board
    private static final Color boxColor = new Color(31, 160, 239);
    // the line thickness value for the boundaries around the board
    // (it is twice the value used for the tiles as only half of it is visible)
    private static final double lineThickness = 0.02;
    public static final int SIZE = 3;


    // Data fields: the instance variables
    // --------------------------------------------------------------------------
    // a matrix to store the tiles on the board in their current configuration
    private Tile[][] tiles = new Tile[3][3];
    // the row and the column indexes of the empty cell
    private int emptyCellRow, emptyCellCol;

    // The default constructor creates a random board
    // --------------------------------------------------------------------------
    public Board() {
        // create an array that contains each number from 0 to 8
        int[] numbers = new int[9];
        for (int i = 0; i < 9; i++)
            numbers[i] = i;
        // randomly shuffle the numbers in the array by using the randomShuffling
        // method defined below
        randomShuffling(numbers);

        // create the tiles and the empty cell on the board by using the randomly
        // shuffled numbers from 0 to 8 and store them in the tile matrix
        int arrayIndex = 0; // the index of the current number in the numbers array
        // for each tile in the tile matrix
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                // create a tile if the current value in the numbers array is not 0
                if (numbers[arrayIndex] != 0)
                    // create a tile with the current number and assign this tile to
                    // the current cell of the tile matrix
                    tiles[row][col] = new Tile(numbers[arrayIndex]);
                    // otherwise, this is an empty cell
                else {
                    // assign the row and the column indexes of the empty cell
                    emptyCellRow = row;
                    emptyCellCol = col;
                }
                // increase the array index by 1
                arrayIndex++;
            }
    }


    // The method(s) of the Board class
    // --------------------------------------------------------------------------
    // An inner method that randomly reorders the elements in a given int array.
    private void randomShuffling(int[] array) {
        // loop through all the elements in the array
        for (int i = 0; i < array.length; i++) {
            // create a random index in the range [0, array.length - 1]
            int randIndex = (int) (Math.random() * array.length);
            // swap the current element with the randomly indexed element
            if (i != randIndex) {
                int temp = array[i];
                array[i] = array[randIndex];
                array[randIndex] = temp;
            }
        }
    }

    public void moveRight() {
        // replace the empty cell with the tile on its right
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow][emptyCellCol + 1];
        tiles[emptyCellRow][emptyCellCol + 1] = null;
        // update the column index of the empty cell
        emptyCellCol++;
    }

    public void moveLeft() {
        // replace the empty cell with the tile on its left
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow][emptyCellCol - 1];
        tiles[emptyCellRow][emptyCellCol - 1] = null;
        // update the column index of the empty cell
        emptyCellCol--;
    }

    public void moveUp() {
        // replace the empty cell with the tile above it
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow - 1][emptyCellCol];
        tiles[emptyCellRow - 1][emptyCellCol] = null;
        // update the row index of the empty cell
        emptyCellRow--;
    }

    public void moveDown() {
        // replace the empty cell with the tile below it
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow + 1][emptyCellCol];
        tiles[emptyCellRow + 1][emptyCellCol] = null;
        // update the row index of the empty cell
        emptyCellRow++;
    }


    // A method for drawing the board by using the StdDraw library
    public void draw() {
        // clear the drawing canvas using the background color
        StdDraw.clear(backgroundColor);
        // for each tile in the tile matrix
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                // skip the empty cell
                if (tiles[row][col] == null)
                    continue;
                // get the position of the tile based on its indexes
                // by using the getTilePosition method defined below
                Point tilePosition = getTilePosition(row, col);
                // draw the tile centered on its position
                tiles[row][col].draw(tilePosition.x, tilePosition.y);

            }
        // draw the box around the board
        StdDraw.setPenColor(boxColor);
        StdDraw.setPenRadius(lineThickness);
        StdDraw.square(2, 2, 1.5);
        StdDraw.setPenRadius(); // reset pen radius to its default value
    }

    // An inner method that returns the position of the tile on the board
    // with the given row and column indexes
    private Point getTilePosition(int rowIndex, int columnIndex) {
        // convert the indexes to the positions in StdDraw
        int posX = columnIndex + 1, posY = 3 - rowIndex;
        return new Point(posX, posY);
    }

    public int[][] getCurrentBoardState() {
        int[][] currentState = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j] == null) {
                    currentState[i][j] = 0;
                } else {
                    currentState[i][j] = tiles[i][j].getNumber();
                }
            }
        }
        return currentState;
    }


}