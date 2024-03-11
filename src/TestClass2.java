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

        State testState = new State(boardState, 0, 0, 0, "");

        Function<int[][], Integer> Heuristic = HeuristicFunctions::manhattan;
//
        State.heuristicFunction = Heuristic;

        int cost = testState.cost();
        System.out.println("Calculated cost: " + cost);


    }
}
