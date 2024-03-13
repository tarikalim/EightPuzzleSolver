/** This classes aim is comparing each heuristic accoring to their execution time.
 *  We can also see that without heuristic, what will be the performance of A*.
 * You can run this class and see the results from the terminal directly.
 */
public class TestClass3 {
    public static void main(String[] args) {
        int[][] testTiles = {
                {2, 5, 6},
                {7, 1, 0},
                {4, 8, 3}
        };

        // Test for the solve method with manhattan.
        Board testBoardWithManhattan = new Board(testTiles);
        State.heuristicFunction = HeuristicFunctions::manhattan;

        long startTimeWithHeuristic = System.nanoTime();
        String solutionWithHeuristic = AStarSolver.solve(testBoardWithManhattan);
        long endTimeWithHeuristic = System.nanoTime();

        long durationWithHeuristic = (endTimeWithHeuristic - startTimeWithHeuristic);
        System.out.println("Duration with manhattan: " + durationWithHeuristic + " nanosecond. Solution path: " + solutionWithHeuristic);

        // Test for the solve method with misplacedTiles
        Board testBoardWithMisPlacedTiles = new Board(testTiles);
        State.heuristicFunction = HeuristicFunctions::misplacedTiles;

        long startTimeWithMisPlacedTiles = System.nanoTime();
        String solutionWithMisPlacedTiles = AStarSolver.solve(testBoardWithMisPlacedTiles);
        long endTimeWithMisPlacedTiles = System.nanoTime();

        long durationWithMissPlacedTiles = (endTimeWithMisPlacedTiles - startTimeWithMisPlacedTiles);
        System.out.println("Duration with Misplaced Tile: " + durationWithMissPlacedTiles + " nanosecond. Solution path: " + solutionWithMisPlacedTiles);


        // Test for the solve method without a heuristic.
        State.heuristicFunction = board -> 0;
        Board testBoardWithoutHeuristic = new Board(testTiles);

        long startTimeWithoutHeuristic = System.nanoTime();
        String solutionWithoutHeuristic = AStarSolver.solve(testBoardWithoutHeuristic);
        long endTimeWithoutHeuristic = System.nanoTime();

        long durationWithoutHeuristic = (endTimeWithoutHeuristic - startTimeWithoutHeuristic);
        System.out.println("Duration without Heuristic: " + durationWithoutHeuristic + " nanosecond. Solution path: " + solutionWithoutHeuristic);
    }
}
