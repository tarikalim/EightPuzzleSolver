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
        State.heuristicFunction = heuristic1; // Heuristik fonksiyonu ayarla

        long startTimeWithHeuristic = System.nanoTime();
        String solutionWithHeuristic = AStarSolver.solve(testBoardWithManhattan);
        long endTimeWithHeuristic = System.nanoTime();

        long durationWithHeuristic = (endTimeWithHeuristic - startTimeWithHeuristic); // Nanosaniye olarak süre
        System.out.println("Manhattan ile çözüm süresi: " + durationWithHeuristic + " nanosaniye. Çözüm yolu: " + solutionWithHeuristic);

        Board testBoardWithMisPlacedTiles = new Board(testTiles);
        Function<int[][], Integer> heuristic2 = HeuristicFunctions::misplacedTiles;
        State.heuristicFunction = heuristic2; // Heuristik fonksiyonu ayarla

        long startTimeWithMisPlacedTiles = System.nanoTime();
        String solutionWithMisPlacedTiles = AStarSolver.solve(testBoardWithMisPlacedTiles);
        long endTimeWithMisPlacedTiles = System.nanoTime();

        long durationWithMissPlacedTiles = (endTimeWithMisPlacedTiles - startTimeWithMisPlacedTiles); // Nanosaniye olarak süre
        System.out.println("Miss Placed ile çözüm süresi: " + durationWithMissPlacedTiles + " nanosaniye. Çözüm yolu: " + solutionWithMisPlacedTiles);


        // Test for the solve method without heuristic.
        State.heuristicFunction = board -> 0; // Heuristiği etkisiz hale getir
        Board testBoardWithoutHeuristic = new Board(testTiles);

        long startTimeWithoutHeuristic = System.nanoTime();
        String solutionWithoutHeuristic = AStarSolver.solve(testBoardWithoutHeuristic);
        long endTimeWithoutHeuristic = System.nanoTime();

        long durationWithoutHeuristic = (endTimeWithoutHeuristic - startTimeWithoutHeuristic); // Nanosaniye olarak süre
        System.out.println("Heuristik olmadan çözüm süresi: " + durationWithoutHeuristic + " nanosaniye. Çözüm yolu: " + solutionWithoutHeuristic);
    }
}
