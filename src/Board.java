import java.awt.Color; // used for coloring the board
import java.awt.Point; // used for the positions of the tiles and the empty cell


// A class that is used for modeling the board in the 8 puzzle.
public class Board {
    // Data fields: the class variables (actually constants here)
    // --------------------------------------------------------------------------
    // the background color used for the empty cell on the board
    public static final Color backgroundColor = new Color(145, 234, 255);
    // the color used for drawing the boundaries around the board
    public static final Color boxColor = new Color(31, 160, 239);
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
        int arrayIndex = 0; // the index of the current number in the number array
        // for each tile in the tile matrix
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                // create a tile if the current value in the number array is not 0
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

    /**
     * Constructor method to copy created Board.
     * We define this constructor because in the main, after we created a Board object
     * user can play and update Board config.
     * To Be able to solve a first version of Board, after creating
     * new Board, we are calling this constructor and copying the first created Board to use it in solver method.
     *
     * @param otherBoard copy of the first created board.
     */
    public Board(Board otherBoard) {
        this.tiles = new Tile[otherBoard.tiles.length][otherBoard.tiles[0].length];
        for (int i = 0; i < otherBoard.tiles.length; i++) {
            for (int j = 0; j < otherBoard.tiles[i].length; j++) {
                if (otherBoard.tiles[i][j] != null) {
                    this.tiles[i][j] = new Tile(otherBoard.tiles[i][j]);
                }
            }
        }
        this.emptyCellRow = otherBoard.emptyCellRow;
        this.emptyCellCol = otherBoard.emptyCellCol;
    }

    /**
     * Test constructor, in this way, you can generate a manual 2d array as a representation of Board
     * to test methods.
     *
     * @param initialTiles board configuration.
     */
    public Board(int[][] initialTiles) {
        this.tiles = new Tile[initialTiles.length][initialTiles[0].length];
        for (int i = 0; i < initialTiles.length; i++) {
            for (int j = 0; j < initialTiles[i].length; j++) {
                this.tiles[i][j] = new Tile(initialTiles[i][j]);
            }
        }
    }


    // The method(s) of the Board class
    // --------------------------------------------------------------------------
    // An inner method that randomly reorders the elements in a given int array.
    private void randomShuffling(int[] array) {
        boolean isSolvable = false;
        while (!isSolvable) {
            for (int i = 0; i < array.length; i++) {
                int randIndex = (int) (Math.random() * array.length);
                int temp = array[i];
                array[i] = array[randIndex];
                array[randIndex] = temp;
            }
            int inversionCount = countInversions(array);
            isSolvable = inversionCount % 2 == 0;
        }
    }


    /**
     * An inner method that count the inversion of the element in the given array,
     * we are using this method with randomShuffling, in this way randomShuffling
     * will loop if the generated configuration is not solvable, according to countInversion.
     *
     * @param array Current states array representation.
     * @return Inversion count of the given array.
     */
    private int countInversions(int[] array) {
        int inversionCount = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j] && array[i] != 0 && array[j] != 0) {
                    inversionCount++;
                }
            }
        }
        return inversionCount;
    }


    // A method for moving the empty cell right
    public void moveRight() {
        // the empty cell cannot go right if it is already at the rightmost column
        if (emptyCellCol == 2)
            return; // return false as the empty cell cannot be moved
        // replace the empty cell with the tile on its right
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow][emptyCellCol + 1];
        tiles[emptyCellRow][emptyCellCol + 1] = null;
        // update the column index of the empty cell
        emptyCellCol++;
        // return true as the empty cell is moved successfully
    }

    // A method for moving the empty cell left
    public void moveLeft() {
        // the empty cell cannot go left if it is already at the leftmost column
        if (emptyCellCol == 0)
            return; // return false as the empty cell cannot be moved
        // replace the empty cell with the tile on its left
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow][emptyCellCol - 1];
        tiles[emptyCellRow][emptyCellCol - 1] = null;
        // update the column index of the empty cell
        emptyCellCol--;
        // return true as the empty cell is moved successfully
    }

    // A method for moving the empty cell up
    public void moveUp() {
        // the empty cell cannot go up if it is already at the topmost row
        if (emptyCellRow == 0)
            return; // return false as the empty cell cannot be moved
        // replace the empty cell with the tile above it
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow - 1][emptyCellCol];
        tiles[emptyCellRow - 1][emptyCellCol] = null;
        // update the row index of the empty cell
        emptyCellRow--;
        // return true as the empty cell is moved successfully
    }

    // A method for moving the empty cell down
    public void moveDown() {
        // the empty cell cannot go down if it is already at the bottommost row
        if (emptyCellRow == 2)
            return; // return false as the empty cell cannot be moved
        // replace the empty cell with the tile below it
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow + 1][emptyCellCol];
        tiles[emptyCellRow + 1][emptyCellCol] = null;
        // update the row index of the empty cell
        emptyCellRow++;
        // return true as the empty cell is moved successfully
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


    /**
     * This method will generate a 2d array representation of a created Board object.
     * This method is important because we don't want access directly to a Board object.
     * In this way, we are making a copy of given Board's representation as an array and
     * working on that copy.
     *
     * @return currentState 2d array representation.
     */
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