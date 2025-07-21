import java.util.Scanner;

public class TicTacToe {
    // Constants defining board size
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // loop allowing players to keep playing new rounds
        do {
            // Clear the board for a new game
            clearBoard();

            // set the first player to X
            String currentPlayer = "X";

            // initializing the number of moves
            int moveCount = 0;

            // loop allowing the game to continue until there's a win or a tie
            while (true) {
                // displaying the current board
                displayBoard();

                // asking the current player for a row and column
                int row = SafeInput.getRangedInt(in, currentPlayer + " Pick a row", 1, ROWS) - 1;
                int col = SafeInput.getRangedInt(in, currentPlayer + " Pick a column", 1, COLS) - 1;

                // if the spot is already taken
                while (!isValidMove(row, col)) {
                    System.out.println("This spot is already taken. Try again.");
                    row = SafeInput.getRangedInt(in, currentPlayer + " Pick a row", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(in, currentPlayer + " Pick a column", 1, COLS) - 1;
                }

                // placing the mark at the chosen location
                board[row][col] = currentPlayer;

                // increase the move counter
                moveCount++;

                // starting on move 5, check for a win
                if (moveCount >= 5 && isWin(currentPlayer)) {
                    // show the board before announcing the winner
                    displayBoard();
                    System.out.println(currentPlayer + " wins!");
                    break;
                }

                // starting on move 6, check for early tie (all win paths blocked, but board not full)
                if (moveCount >= 6 && moveCount < ROWS * COLS && isEarlyTie()) {
                    displayBoard();
                    System.out.println("It's an early tie! No possibility to win.");
                    break;
                }

                // if 9 moves played and no win, it's a full-board tie
                if (moveCount == ROWS * COLS) {
                    displayBoard();
                    System.out.println("It's a tie!");
                    break;
                }

                // toggle the player
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
            // ask if players want to continue to play
        } while (SafeInput.getYNConfirm(in, "Play again?"));

        // exit the game
        System.out.println("Thanks for playing Tic Tac Toe!");
    }

    // helper methods
    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void displayBoard() {
        System.out.print("\n   ");
        for (int col = 1; col <= COLS; col++) {
            System.out.print(col + "   ");
        }
        System.out.println();
        for (int row = 0; row < ROWS; row++) {
            System.out.print((row + 1) + " "); // Display row number
            for (int col = 0; col < COLS; col++) {
                System.out.print(" " + board[row][col]);
                if (col < COLS - 1) System.out.print(" |");
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.print("  ");
                for (int col = 0; col < COLS - 1; col++) {
                    System.out.print("---+");
                }
                System.out.println("---");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            boolean win = true;
            for (int col = 0; col < COLS; col++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            boolean win = true;
            for (int row = 0; row < ROWS; row++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        boolean winDiagonal1 = true;
        boolean winDiagonal2 = true;
        for (int i = 0; i < ROWS; i++) {
            if (!board[i][i].equals(player)) winDiagonal1 = false;
            if (!board[i][COLS - i - 1].equals(player)) winDiagonal2 = false;
        }
        return winDiagonal1 || winDiagonal2;
    }

    // Early tie check: returns true if every row, col, and diagonal is blocked (contains both X and O)
    private static boolean isEarlyTie() {
        // Define all win vectors
        int[][][] winVectors = {
                { {0,0},{0,1},{0,2} }, { {1,0},{1,1},{1,2} }, { {2,0},{2,1},{2,2} },
                { {0,0},{1,0},{2,0} }, { {0,1},{1,1},{2,1} }, { {0,2},{1,2},{2,2} },
                { {0,0},{1,1},{2,2} }, { {0,2},{1,1},{2,0} }
        };

        for (int[][] vector : winVectors) {
            boolean xPossible = true;
            boolean oPossible = true;
            for (int[] cell : vector) {
                String mark = board[cell[0]][cell[1]];
                if (mark.equals("O")) xPossible = false;
                if (mark.equals("X")) oPossible = false;
            }
            // If either player can still win on this vector, return false (not a tie)
            if (xPossible || oPossible) return false;
        }
        // No win line open: true tie
        return true;
    }
}