class Solution {
  static final int[] R_OFFSETS = {-1, -1, 0, 1, 1, 1, 0, -1};
  static final int[] C_OFFSETS = {0, 1, 1, 1, 0, -1, -1, -1};

  public void gameOfLife(int[][] board) {
    int row = board.length;
    int col = board[0].length;

    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        int neighbor = countNeighbor(board, r, c);
        if (getPrevious(board, r, c) == 1) {
          if (neighbor == 2 || neighbor == 3) {
            setCurrent(board, r, c, 1);
          } else {
            setCurrent(board, r, c, 0);
          }
        } else {
          if (neighbor == 3) {
            setCurrent(board, r, c, 1);
          } else {
            setCurrent(board, r, c, 0);
          }
        }
      }
    }

    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        board[r][c] = getCurrent(board, r, c);
      }
    }
  }

  int countNeighbor(int[][] board, int r, int c) {
    int row = board.length;
    int col = board[0].length;

    int result = 0;
    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int neighborR = r + R_OFFSETS[i];
      int neighborC = c + C_OFFSETS[i];
      if (neighborR >= 0
          && neighborR < row
          && neighborC >= 0
          && neighborC < col
          && getPrevious(board, neighborR, neighborC) == 1) {
        ++result;
      }
    }

    return result;
  }

  int getPrevious(int[][] board, int r, int c) {
    return board[r][c] & 1;
  }

  void setCurrent(int[][] board, int r, int c, int current) {
    board[r][c] = (current << 1) + getPrevious(board, r, c);
  }

  int getCurrent(int[][] board, int r, int c) {
    return board[r][c] >> 1;
  }
}
