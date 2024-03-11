// Class to compare heuristic functions and also performance of algorithm without any heuristic function
import java.util.function.Function;

public class TestClass3 {
    public static void main(String[] args) {
        int[][] testTiles = {
                {2, 5, 6},
                {7, 1, 0},
                {4, 8, 3}
        };

        // Test for the solve method with heuristic.
        Board testBoardWithManhattan = new Board(testTiles);
        Function<int[][], Integer> heuristic1 = HeuristicFunctions::manhattan;
        State.heuristicFunction = heuristic1;

        long startTimeWithHeuristic = System.nanoTime();
        String solutionWithHeuristic = AStarSolver.solve(testBoardWithManhattan);
        long endTimeWithHeuristic = System.nanoTime();

        long durationWithHeuristic = (endTimeWithHeuristic - startTimeWithHeuristic);
        System.out.println("Duration with manhattan: " + durationWithHeuristic + " nanosecond. Solution path: " + solutionWithHeuristic);

        Board testBoardWithMisPlacedTiles = new Board(testTiles);
        Function<int[][], Integer> heuristic2 = HeuristicFunctions::misplacedTiles;
        State.heuristicFunction = heuristic2;

        long startTimeWithMisPlacedTiles = System.nanoTime();
        String solutionWithMisPlacedTiles = AStarSolver.solve(testBoardWithMisPlacedTiles);
        long endTimeWithMisPlacedTiles = System.nanoTime();

        long durationWithMissPlacedTiles = (endTimeWithMisPlacedTiles - startTimeWithMisPlacedTiles); // Nanosaniye olarak süre
        System.out.println("Duration with Misplaced Tile: " + durationWithMissPlacedTiles + " nanosecond. Solution path: " + solutionWithMisPlacedTiles);


        // Test for the solve method without heuristic.
        State.heuristicFunction = board -> 0;
        Board testBoardWithoutHeuristic = new Board(testTiles);

        long startTimeWithoutHeuristic = System.nanoTime();
        String solutionWithoutHeuristic = AStarSolver.solve(testBoardWithoutHeuristic);
        long endTimeWithoutHeuristic = System.nanoTime();

        long durationWithoutHeuristic = (endTimeWithoutHeuristic - startTimeWithoutHeuristic);
        System.out.println("Duration without Heuristic: " + durationWithoutHeuristic + " nanosecond. Solution path: " + solutionWithoutHeuristic);
    }
}
