public class SudokuSolver {

    public static void main(String[] args) {
        // I define a sample Sudoku board with empty cells represented as '.'
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        // I call the method to solve the Sudoku puzzle
        solveSudoku(board);

        // I print the completed Sudoku board to the console
        printBoard(board);
    }

    // This method is where I solve the Sudoku puzzle
    public static boolean solveSudoku(char[][] board) {
        // I loop through each row of the 9x9 board
        for (int row = 0; row < 9; row++) {
            // I loop through each column in the current row
            for (int col = 0; col < 9; col++) {
                // I check if the current cell is empty
                if (board[row][col] == '.') {
                    // I try placing digits '1' to '9' in the empty cell
                    for (char num = '1'; num <= '9'; num++) {
                        // I check if placing the number is valid
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // I place the number in the cell

                            // I recursively try to solve the rest of the Sudoku board
                            if (solveSudoku(board)) {
                                return true; // I return true if the board is solved
                            }

                            // If placing num doesn't lead to a solution, I reset the cell and backtrack
                            board[row][col] = '.';
                        }
                    }
                    return false; // If I can't find a valid number, I trigger backtracking
                }
            }
        }
        return true; // I return true when the Sudoku puzzle is solved
    }

    // This method checks if a number can be placed in the specified cell
    public static boolean isValid(char[][] board, int row, int col, char num) {
        // I check if the number is already present in the current row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false; // The number cannot be placed, so I return false
            }
        }

        // I check if the number is already present in the current column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false; // The number cannot be placed, so I return false
            }
        }

        // I check if the number is present in the 3x3 sub-grid
        int startRow = (row / 3) * 3; // I find the starting row of the 3x3 box
        int startCol = (col / 3) * 3; // I find the starting column of the 3x3 box
        for (int i = 0; i < 3; i++) { // I loop through rows of the 3x3 box
            for (int j = 0; j < 3; j++) { // I loop through columns of the 3x3 box
                if (board[startRow + i][startCol + j] == num) {
                    return false; // The number is found in the box, so I cannot place it, and I return false
                }
            }
        }

        return true; // The placement of the number is valid, so I return true
    }

    // This method prints the Sudoku board
    public static void printBoard(char[][] board) {
        // I loop through each row of the board
        for (int i = 0; i < 9; i++) {
            // I loop through each column in the current row
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " "); // I print the current cell
            }
            System.out.println(); // I move to the next line after printing a row
        }
    }
}
