package hw11;

/* 
 * find a solution to a Sudoku problem
 * @author	Biagioni, Edoardo
 * @author  Frances Uy
 * @date	October 23, 2013
 * @missing	fillSudoku, to be implemented by the students in ICS 211
 */
public class Sudoku {

  /* check that the sudoku rules hold in this sudoku puzzle.
   * cells that contain 0 are not checked.
   * @param the sudoku to be checked
   * @param whether to print the error found, if any
   * @return true if this sudoku obeys all of the sudoku rules, otherwise false
   */
  public static boolean checkSudoku (int [] [] sudoku, boolean printErrors)
  {
    if (sudoku.length != 9) {
      if (printErrors) {
        System.out.println ("sudoku has " + sudoku.length +
                            " rows, should have 9");
      }
      return false;
    }
    for (int i = 0; i < sudoku.length; i++) {
      if (sudoku [i].length != 9) {
        if (printErrors) {
          System.out.println ("sudoku row " + i + " has " +
                              sudoku [i].length + " cells, should have 9");
        }
        return false;
      }
    }
    /* check each cell for conflicts */
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku.length; j++) {
        int cell = sudoku [i] [j];
        if (cell == 0) {
          continue;   /* blanks are always OK */
        }
        if ((cell < 1) || (cell > 9)) {
          if (printErrors) {
            System.out.println ("sudoku row " + i + " column " + j +
                                " has illegal value " + cell);
          }
          return false;
        }
        /* does it match any other value in the same row? */
        for (int m = 0; m < sudoku.length; m++) {
          if ((j != m) && (cell == sudoku [i] [m])) {
            if (printErrors) {
              System.out.println ("sudoku row " + i + " has " + cell +
                                  " at both positions " + j + " and " + m);
            }
            return false;
          }
        }
        /* does it match any other value it in the same column? */
        for (int k = 0; k < sudoku.length; k++) {
          if ((i != k) && (cell == sudoku [k] [j])) {
            if (printErrors) {
              System.out.println ("sudoku column " + j + " has " + cell +
                                  " at both positions " + i + " and " + k);
            }
            return false;
          }
        }
        /* does it match any other value in the 3x3? */
        for (int k = 0; k < 3; k++) {
          for (int m = 0; m < 3; m++) {
            int testRow = (i / 3 * 3) + k;   /* test this row */
            int testCol = (j / 3 * 3) + m;   /* test this col */
            if ((i != testRow) && (j != testCol) &&
                (cell == sudoku [testRow] [testCol])) {
              if (printErrors) {
                System.out.println ("sudoku character " + cell + " at row " +
                                    i + ", column " + j + 
                                    " matches character at row " + testRow +
                                    ", column " + testCol);
              }
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  /* convert the sudoku to a printable string
   * @param the sudoku to be converted
   * @param whether to check for errors
   * @return the printable version of the sudoku
   */
  public static String toString (int [] [] sudoku, boolean debug) {
    if ((! debug) || (checkSudoku (sudoku, true))) {
      String result = "";
      for (int i = 0; i < sudoku.length; i++) {
        if (i % 3 == 0) {
          result = result + "+-------+-------+-------+\n";
        }
        for (int j = 0; j < sudoku.length; j++) {
          if (j % 3 == 0) {
            result = result + "| ";
          }
          if (sudoku [i] [j] == 0) {
            result = result + "  ";
          } else {
            result = result + sudoku [i] [j] + " ";
          }
        }
        result = result + "|\n";
      }
      result = result + "+-------+-------+-------+\n";
      return result;
    }
    return "illegal sudoku";
  }

  /*
   * find an assignment of values to sudoku cells that makes the sudoku valid
   * @param the sudoku to be filled
   * @return whether a solution was found 
   *    if a solution was found, the sudoku is filled in with the solution
   *    if no solution was found, restores the sudoku to its original value
   */
  public static boolean fillSudoku (int [][] sudoku) {
      //keeps track of whether or not the puzzle has been fully filled
      boolean allFilled = true;
      //do not forget to update these values later
      int row = -1;
      int col = -1;

      //nested for-loop to find the next empty cell
      //if an empty cell is found, then the row and column indexes of that cell
      //need to be assigned to row and col respectively
      //this tells us the location of the cell that needs to be filled
      for (int i = 0; i < sudoku.length; i++) {
          for (int j = 0; j < sudoku.length; j++) {
              int cell = sudoku[i][j];
              if (cell == 0) {
                  row = i;
                  col = j;
                  allFilled = false;
                  break;
              }
          }
          if (!allFilled) {
              break;
          }
      }

      if (allFilled) {
    	// solution found
          return true; 
      }

      // try each number from 1 to 9 in the empty cell by assigning num to sudoku[row][col]
      // check to see if the resulting grid is still valid using the checkSudoku() method
      // if the grid is valid, the code proceeds to check if the rest of the grid
      // can be filled recursively by calling the fillSudoku() method on the updated grid
      // otherwise, the loop continues to try the next number.
      for (int num = 1; num <= 9; num++) {
          sudoku[row][col] = num;
          if (checkSudoku(sudoku, allFilled)) {
              if (fillSudoku(sudoku)) {
            	// solution found
                  return true; 
              }
          }
          
          
          // restoring the assignment
          // try the next number, still in for loop
          sudoku[row][col] = 0; 
          
      }
      //no solution found
      return false; 
  }

}
