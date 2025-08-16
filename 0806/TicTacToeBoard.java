import java.util.*;

public class TicTacToeBoard {
    static char[][] board = new char[3][3];

    public static void initBoard() {
        for (char[] row : board) Arrays.fill(row, ' ');
    }

    public static boolean place(int x, int y, char symbol) {
        if (x < 0 || x >= 3 || y < 0 || y >= 3 || board[x][y] != ' ')
            return false;
        board[x][y] = symbol;
        return true;
    }

    public static boolean checkWin(char symbol) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol))
                return true;

        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    public static boolean isFull() {
        for (char[] row : board)
            for (char c : row)
                if (c == ' ') return false;
        return true;
    }

    public static void printBoard() {
        for (char[] row : board) {
            for (char c : row) System.out.print(c + " | ");
            System.out.println("\b\b\b ");
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initBoard();
        char current = 'X';
        while (true) {
            printBoard();
            System.out.println("Player " + current + ", enter row and column (0-2):");
            int r = sc.nextInt(), c = sc.nextInt();
            if (!place(r, c, current)) {
                System.out.println("Invalid move!");
                continue;
            }
            if (checkWin(current)) {
                printBoard();
                System.out.println("Player " + current + " wins!");
                break;
            }
            if (isFull()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }
            current = (current == 'X') ? 'O' : 'X';
        }
    }
}
