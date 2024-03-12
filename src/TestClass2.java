// class to check correctness of heuristic functions
// and correctness of cost method.
import java.util.function.Function;

public class TestClass2 {
    public static void main(String[] args) {

        int[][] boardState = {
                {1, 2, 3},
                {4, 5, 8},
                {7, 0, 6}
        };
        State.heuristicFunction = HeuristicFunctions::misplacedTiles;

        State testState = new State(boardState, 0, 0, 0, "");

        int cost = testState.getCost();
        System.out.println("Calculated cost: " + cost);
    }
}
