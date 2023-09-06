# recursive-sudoku-solver
A Sudoku Solver using a Backtracking algorithm in Java.
This was an assignment for my ICS 211, Introduction to Computer Science II class at UH Mānoa. 

## What is Sudoku?
Sudoku puzzles are structured as a 9x9 grid of integers, where each cell holds a value between 1 and 9. The puzzle is deemed solved when each of the 9 rows, 9 columns, and 9 3x3 boxes within the grid contains all the numbers from 1 to 9 exactly once, without any duplicates.

A Sudoku problem essentially involves a partially filled Sudoku grid, and the objective is to populate the remaining empty cells in a valid manner to complete the puzzle.

## SudokuTest.java File Description
SudokuTest.java provides three sample Sudoku problems, and solutions are provided for the first two.

The recursive approach for Sudoku problem-solving can be summarized as follows:

If all cells are filled, validate whether the Sudoku is a solution. If it is, a solution has been discovered. If not, the Sudoku cannot be solved.

If at least one cell is empty, determine which values are legally allowed in that cell:

If no values are valid, the Sudoku cannot be solved.
If one or more values are valid, sequentially place each valid value into the cell and recursively attempt to solve the Sudoku, filling in the remaining empty cells.
If a solution is found for at least one valid value, update the Sudoku with this solution and indicate that a solution has been found.

If no solution is found for any valid value, reset the cell to its original value and report that the Sudoku does not have a solution.

During this recursive process, it is essential to revert the Sudoku grid to its previous state when backtracking.

This algorithm is a classic example of backtracking, where you explore all available options and backtrack if an option leads to an invalid solution.

## Sudoku.java File Description
The code required to check Sudoku validity can be found in Sudoku.java, specifically in the CheckSudoku method, which returns true for valid Sudoku puzzles and false for invalid ones. The toString method converts a Sudoku into a printable format and can also perform validity checks.

The Sudoku grid is represented as a 9x9 array of integers, where each integer should be in the range of 1 to 9, or 0 to indicate an empty cell.

The CheckSudoku method exclusively verifies whether Sudoku rules are violated and does not confirm if all cells are filled correctly.
