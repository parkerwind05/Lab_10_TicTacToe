import java.util.Scanner;

public class TicTacToe
{
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];
    private static String currentPlayer = "X";

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        while(playAgain)
        {
            clearBoard();
            display();

            int moveCount = 0;

            while(true)
            {
                System.out.println("Player " + currentPlayer + "'s turn. ");
                int row = SafeInput.getRangedInt(in, "Enter row", 1, 3) -1;
                int col = SafeInput.getRangedInt(in, "Enter column", 1, 3) -1;

                if (!isValidMove(row, col))
                {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }

                board[row][col] = currentPlayer;
                moveCount++;
                display();

                if (isWin(currentPlayer))
                {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (isTie()) {
                    System.out.println("It's a tie!");
                    break;
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";


            }
            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again?");
        }

        System.out.println("Thanks for playing!");



    }

    private static void clearBoard()
    {
        for (int row =0; row<ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    private static void display()
    {
        for (int row =0; row<ROWS; row++)
        {
            for (int col=0; col<COLS; col++)
            {
                System.out.print(" " + board[row][col] + " ");
                if(col<COLS-1)
                    System.out.print("|");

            }
            System.out.println();
            if(row<ROWS-1)
                System.out.println("---|---|---");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }


}