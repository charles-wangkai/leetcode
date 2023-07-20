public class Solution {
  public int[][] candyCrush(int[][] board) {
    int row = board.length;
    int col = board[0].length;

    while (true) {
      boolean hasCrush = false;
      boolean[][] crushes = new boolean[row][col];
      for (int r = 0; r < row; r++) {
        for (int c = 0; c < col; c++) {
          if (board[r][c] != 0) {
            hasCrush |= search(board, crushes, r, c, 0, 1);
            hasCrush |= search(board, crushes, r, c, 1, 0);
          }
        }
      }
      if (!hasCrush) {
        return board;
      }

      for (int c = 0; c < col; c++) {
        int bottomR = row - 1;
        for (int r = row - 1; r >= 0; r--) {
          if (!crushes[r][c]) {
            board[bottomR][c] = board[r][c];
            bottomR--;
          }
        }
        for (int r = bottomR; r >= 0; r--) {
          board[r][c] = 0;
        }
      }
    }
  }

  boolean search(
      int[][] board, boolean[][] crushes, int startR, int startC, int offsetR, int offsetC) {
    int row = board.length;
    int col = board[0].length;

    int length = 0;
    for (int r = startR, c = startC;
        r >= 0 && r < row && c >= 0 && c < col && board[r][c] == board[startR][startC];
        r += offsetR, c += offsetC) {
      length++;
    }

    if (length < 3) {
      return false;
    }

    for (int i = 0; i < length; i++) {
      crushes[startR + i * offsetR][startC + i * offsetC] = true;
    }
    return true;
  }
}
