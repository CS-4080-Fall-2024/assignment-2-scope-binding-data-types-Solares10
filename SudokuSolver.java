public class SudokuSolver {

    public static void main(String[] args) {
        // Example Sudoku board with empty cells (denoted by '.')
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

        // Call the solve function to fill the board
        solveSudoku(board);

        // Print the solved board
        printBoard(board);
    }

    public static boolean solveSudoku(char[][] board) {
        // Traverse through the 9x9 board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Check if the cell is empty (i.e., '.')
                if (board[row][col] == '.') {
                    // Try placing digits from '1' to '9'
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // Place the number

                            // Recursively attempt to solve the rest of the board
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // If placing num doesn't lead to a solution, backtrack
                            board[row][col] = '.';
                        }
                    }
                    return false; // No valid number was found, trigger backtracking
                }
            }
        }
        return true; // Sudoku solved
    }

    public static boolean isValid(char[][] board, int row, int col, char num) {
        // Check if num is already in the current row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if num is already in the current column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if num is already in the current 3x3 sub-box
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true; // The placement is valid
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
