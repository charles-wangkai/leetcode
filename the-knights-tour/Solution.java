import java.util.Arrays;

class Solution {
  static final int[] R_OFFSETS = {-2, -2, -1, -1, 1, 1, 2, 2};
  static final int[] C_OFFSETS = {-1, 1, -2, 2, -2, 2, -1, 1};

  public int[][] tourOfKnight(int m, int n, int r, int c) {
    int[][] board = new int[m][n];
    for (int i = 0; i < board.length; ++i) {
      Arrays.fill(board[i], -1);
    }

    search(board, r, c, 0);

    return board;
  }

  boolean search(int[][] board, int r, int c, int step) {
    int m = board.length;
    int n = board[0].length;

    board[r][c] = step;

    if (step == m * n - 1) {
      return true;
    }

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int nextR = r + R_OFFSETS[i];
      int nextC = c + C_OFFSETS[i];
      if (nextR >= 0
          && nextR < m
          && nextC >= 0
          && nextC < n
          && board[nextR][nextC] == -1
          && search(board, nextR, nextC, step + 1)) {
        return true;
      }
    }

    board[r][c] = -1;

    return false;
  }
}
