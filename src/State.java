import java.util.Arrays;
import java.util.function.Function;

public class State implements Comparable<State> {
    private static final int SIZE = 3; // size of the board. Usage in 2d array: [SIZE]X[SIZE]

    private int[][] board;
    private int x;
    private int y;
    private int moves;
    private String path;
    private int cost;

    public static Function<int[][], Integer> heuristicFunction = board -> 0;

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
    public  int getCost(){
        return cost;
    }
// calculate the total cost of current State and set this value to State
// object parameter as cost to use it in priority queue implementation
    public int calculateCost() {
        return moves + heuristicFunction.apply(board);
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
    // in this way we specified the priority of State object
    // when we add them to priority queue.
    @Override
    public int compareTo(State o) {
        return Integer.compare(this.calculateCost(), o.getCost());
    }
}