// Test class for AStarSolver class,
// make public related methods in the solver class
// use the test Board object and comment out.
// Aim of this class is checking correctness of solver class
// and also you can check without heuristic and with heuristic
// what is the runtime difference.

import java.awt.*;
import java.util.function.Function;

public class TestClass {
    public static void main(String[] args) {
        int[][] testTiles = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };
        int[][] testTiles2 = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 5}
        };
        // Test for the solve method.
        Board testBoard = new Board(testTiles);
        Function<int[][], Integer> Heuristic = HeuristicFunctions::misplacedTiles;
        State.heuristicFunction = Heuristic;
        String solution = AStarSolver.solve(testBoard);
        System.out.println("Shortest path: " + solution);

//        Board testBoard2 = new Board(testTiles2);
//        String solution2 = AStarSolver.solve(testBoard2);
//        System.out.println("Short path board 2" + solution2);

        // Test for the empty cell finder method.
//        Point result1 = AStarSolver.findInitialEmptyCellPosition(testTiles);
//        System.out.println(result1);
//        Point result2 = AStarSolver.findInitialEmptyCellPosition(testTiles2);
//        System.out.println(result2);

        // Test for the manhattan distance method
//        int manhattan1 = HeuristicFunctions.manhattan(testTiles2);
//        System.out.println(" manhattan= "+ manhattan1);
//        int manhattan2 = AStarSolver.manhattan(testTiles2);
//        System.out.println(manhattan2);
//        int misPlaced2 = HeuristicFunctions.misplacedTiles(testTiles2);
//        System.out.println("missPlaced: " + misPlaced2);


    }
}