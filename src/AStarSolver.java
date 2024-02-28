import java.util.*;

public class AStarSolver {
    public static final int SIZE = 3;
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final String DIRECTIONS = "UDLR";

    static class State {
        int[][] board;
        int x, y;
        int moves;
        String path;

        State(int[][] board, int x, int y, int moves, String path) {
            this.board = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                this.board[i] = Arrays.copyOf(board[i], SIZE);
            }
            this.x = x;
            this.y = y;
            this.moves = moves;
            this.path = path;
        }

        boolean isGoal() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] != 0 && board[i][j] != i * SIZE + j + 1) {
                        return false;
                    }
                }
            }
            return true;
        }

        int manhattan() {
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

        boolean isValid(int nx, int ny) {
            return nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE;
        }


        static void swap(int[][] board, int x1, int y1, int x2, int y2) {
            int temp = board[x1][y1];
            board[x1][y1] = board[x2][y2];
            board[x2][y2] = temp;
        }

        List<State> getSuccessors() {
            List<State> successors = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isValid(nx, ny)) {
                    State newState = new State(board, nx, ny, moves + 1, path + DIRECTIONS.charAt(i));
                    swap(newState.board, x, y, nx, ny);
                    successors.add(newState);
                }
            }
            return successors;
        }

        int cost() {
            return moves + manhattan();
        }

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
    }

    public static String solve(Board board) {
        int[][] initialStateArray = board.getCurrentBoardState();
        PriorityQueue<State> frontier = new PriorityQueue<>(Comparator.comparingInt(State::cost));
        Set<State> explored = new HashSet<>();

        int x = 0, y = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (initialStateArray[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        State initialState = new State(initialStateArray, x, y, 0, "");
        frontier.add(initialState);

        while (!frontier.isEmpty()) {
            State state = frontier.poll();
            if (state.isGoal()) {
                return state.path;
            }

            if (explored.contains(state)) continue;
            explored.add(state);

            for (State successor : state.getSuccessors()) {
                if (!explored.contains(successor)) {
                    frontier.add(successor);
                }
            }
        }
        return "No solution";
    }
}
