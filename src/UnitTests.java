// Test class for AStarSolver class,
// make public related methods in the solver class
// use the test Board object and comment out.
// import java.awt.*;
//
//public class UnitTests {
//    public static void main(String[] args) {
//        int[][] testTiles = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {0, 7, 8}
//        };
//        int[][] testTiles2 = {
//                {1, 2, 3},
//                {4, 0, 6},
//                {7, 8, 5}
//        };
//        // Test for the solve method.
//        Board testBoard = new Board(testTiles);
//        String solution = AStarSolver.solve(testBoard);
//        System.out.println("Shortest path: " + solution);
//
//        // Test for the empty cell finder method.
//        Point result1 = AStarSolver.findInitialEmptyCellPosition(testTiles);
//        System.out.println(result1);
//        Point result2 = AStarSolver.findInitialEmptyCellPosition(testTiles2);
//        System.out.println(result2);
//
//        // Test for the manhattan distance method
//        int manhattan1 = AStarSolver.manhattan(testTiles);
//        System.out.println(manhattan1);
//        int manhattan2 = AStarSolver.manhattan(testTiles2);
//        System.out.println(manhattan2);
//
//        //
//
//    }
//}
