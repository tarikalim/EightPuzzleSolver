/** Simple class to check Heuristic functions and cost method working fine,
 *  You can give Heuristic as a parameter to cost function to check each of them.
 */

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
