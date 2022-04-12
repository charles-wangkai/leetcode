class Solution {
  public void gameOfLife(int[][] board) {
    int m = board.length;
    int n = board[0].length;

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
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

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        board[r][c] = getCurrent(board, r, c);
      }
    }
  }

  int countNeighbor(int[][] board, int r, int c) {
    int m = board.length;
    int n = board[0].length;

    int result = 0;
    for (int dr = -1; dr <= 1; ++dr) {
      int neighborR = r + dr;
      for (int dc = -1; dc <= 1; ++dc) {
        int neighborC = c + dc;
        if ((dr != 0 || dc != 0)
            && neighborR >= 0
            && neighborR < m
            && neighborC >= 0
            && neighborC < n
            && getPrevious(board, neighborR, neighborC) == 1) {
          ++result;
        }
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
