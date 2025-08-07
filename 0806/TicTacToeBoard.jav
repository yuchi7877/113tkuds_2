public class TicTacToeBoard {
    private char[][] board;
    private static final int SIZE = 3;
    private static final char EMPTY = '.';

    public TicTacToeBoard() {
        board = new char[SIZE][SIZE];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public boolean placeMove(int row, int col, char player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            System.out.println("無效位置！");
            return false;
        }
        if (board[row][col] != EMPTY) {
            System.out.println("該位置已有棋子！");
            return false;
        }
        board[row][col] = player;
        return true;
    }

    public boolean checkWinner(char player) {
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || 
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) || 
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameOver(char player) {
        return checkWinner(player) || isBoardFull();
    }

    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        game.printBoard();
        game.placeMove(0, 0, 'X');
        game.placeMove(0, 1, 'O');
        game.placeMove(1, 1, 'X');
        game.placeMove(0, 2, 'O');
        game.placeMove(2, 2, 'X');

        game.printBoard();

        if (game.checkWinner('X')) {
            System.out.println("X 獲勝！");
        } else if (game.checkWinner('O')) {
            System.out.println("O 獲勝！");
        } else if (game.isBoardFull()) {
            System.out.println("平手！");
        } else {
            System.out.println("遊戲尚未結束。");
        }
    }
}
