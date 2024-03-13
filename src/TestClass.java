/**
 * First test class to check helper methods of algorithms working correctly or not.
 * We are going to use the third constructor of a Board object to manually give an array config
 * You can comment out and try any of the methods to check.
 */

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

        Board testBoard = new Board(testTiles);
        State.heuristicFunction = HeuristicFunctions::misplacedTiles;
        String solution = AStarSolver.solve(testBoard);
        System.out.println("Shortest path: " + solution);

        Board testBoard2 = new Board(testTiles2);
        String solution2 = AStarSolver.solve(testBoard2);
        System.out.println("Short path board 2" + solution2);


        int manhattan1 = HeuristicFunctions.manhattan(testTiles2);
        System.out.println(" manhattan= " + manhattan1);
        int manhattan2 = HeuristicFunctions.manhattan(testTiles2);
        System.out.println(manhattan2);
        int misPlaced2 = HeuristicFunctions.misplacedTiles(testTiles2);
        System.out.println("missPlaced: " + misPlaced2);


    }
}