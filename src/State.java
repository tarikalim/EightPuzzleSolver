import java.awt.*;
import java.util.Arrays;

public class State implements Comparable<State> {
    private int[][] board;
    private int x;
    private int y;
    private int moves;
    private String path;

    public State(int[][] board, int x, int y, int moves, String path) {
        this.board = new int[AStarSolver.SIZE][AStarSolver.SIZE];
        for (int i = 0; i < AStarSolver.SIZE; i++) {
            this.board[i] = Arrays.copyOf(board[i], AStarSolver.SIZE);
        }
        this.x = x;
        this.y = y;
        this.moves = moves;
        this.path = path;
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

    public int cost() {
        return moves + AStarSolver.manhattan(board);
    }

    // equals override to compare two states.
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        for (int i = 0; i < AStarSolver.SIZE; i++) {
            for (int j = 0; j < AStarSolver.SIZE; j++) {
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
        for (int i = 0; i < AStarSolver.SIZE; i++) {
            for (int j = 0; j < AStarSolver.SIZE; j++) {
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
        return Integer.compare(this.cost(), o.cost());
    }
}