
# 8 Puzzle Solver

This project is a visual and interactive solver for the 8-puzzle problem using the **A* Search Algorithm**. 
It includes two heuristic functions: **Manhattan Distance** and **Misplaced Tiles**. Users can interact with the puzzle board, 
play with it using the arrow keys, and solve the puzzle by pressing `ENTER`.

## Project Overview

The 8-puzzle problem consists of a 3x3 grid with numbered tiles and one empty space. The goal is to rearrange t
he tiles into a specific order by sliding them into the empty space. 
This project uses A* search to find the shortest sequence of moves to solve the puzzle.

## Features

- Two heuristic functions: Manhattan Distance and Misplaced Tiles.
- Interactive board controlled by arrow keys.
- A* search algorithm for finding the shortest path to solve the puzzle.
- Visualizes the solution step by step on the board.

## Technologies Used

- **Java**: Main programming language.
- **StdDraw Library**: Used for rendering the graphical interface.
- **A* Search Algorithm**: Core algorithm for solving the puzzle.

## How to Run the Project

### Requirements

- Java 8 or higher
- StdDraw library (Make sure the StdDraw library is properly added to the project)

### Steps to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/tarikalim/EightPuzzleSolver.git
   ```

2. **Open in your IDE**:
   Open the project in your favorite IDE like IntelliJ IDEA.
3. 
3. **Run the Main Class**:
   Run the `EightPuzzle.java` file.

4. **Interact with the Board**:
    - Press **'1'** to select **Manhattan Distance** heuristic.
    - Press **'2'** to select **Misplaced Tiles** heuristic.
    - Use **Arrow Keys** to shuffle the board manually.
    - Press **'Enter'** to solve the puzzle using the A* algorithm.

## How It Works

1. **Heuristic Selection**: The user selects the heuristic function using the keyboard:
    - Press '1' for **Manhattan Distance**.
    - Press '2' for **Misplaced Tiles**.

2. **User Interaction**: Users can manually move the tiles by pressing the arrow keys (Up, Down, Left, Right).

3. **Solving the Puzzle**: When the user presses `ENTER`, the program solves the puzzle using A* search based on the chosen heuristic. The solution is visualized step by step on the puzzle board.

## Algorithms

### A* Search Algorithm

The A* algorithm is used to solve the 8-puzzle problem. It uses the following two heuristics:

- **Manhattan Distance**: The sum of the absolute differences between the current positions and the goal positions of the tiles.
- **Misplaced Tiles**: The number of tiles that are not in their correct position.

The algorithm explores the most promising states first, ensuring that the shortest path to the solution is found.

## Screenshots




![Solution](/images/1.png)
![Solution](/images/2.png)
![Solution](/images/3.png)


