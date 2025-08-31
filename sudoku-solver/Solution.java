class Solution {
  public void solveSudoku(char[][] board) {
    boolean[][] rowSeen = new boolean[9][10];
    boolean[][] colSeen = new boolean[9][10];
    boolean[][][] blockSeen = new boolean[3][3][10];
    for (int r = 0; r < 9; ++r) {
      for (int c = 0; c < 9; ++c) {
        if (board[r][c] != '.') {
          fill(board, rowSeen, colSeen, blockSeen, r, c, board[r][c] - '0');
        }
      }
    }

    search(board, rowSeen, colSeen, blockSeen, 0, 0);
  }

  boolean fill(
      char[][] board,
      boolean[][] rowSeen,
      boolean[][] colSeen,
      boolean[][][] blockSeen,
      int r,
      int c,
      int digit) {
    if (rowSeen[r][digit] || colSeen[c][digit] || blockSeen[r / 3][c / 3][digit]) {
      return false;
    }

    board[r][c] = (char) ('0' + digit);
    rowSeen[r][digit] = true;
    colSeen[c][digit] = true;
    blockSeen[r / 3][c / 3][digit] = true;

    return true;
  }

  void unfill(
      char[][] board,
      boolean[][] rowSeen,
      boolean[][] colSeen,
      boolean[][][] blockSeen,
      int r,
      int c) {
    int digit = board[r][c] - '0';
    board[r][c] = '.';
    rowSeen[r][digit] = false;
    colSeen[c][digit] = false;
    blockSeen[r / 3][c / 3][digit] = false;
  }

  boolean search(
      char[][] board,
      boolean[][] rowSeen,
      boolean[][] colSeen,
      boolean[][][] blockSeen,
      int r,
      int c) {
    if (r == 9) {
      return true;
    }
    if (c == 9) {
      return search(board, rowSeen, colSeen, blockSeen, r + 1, 0);
    }
    if (board[r][c] != '.') {
      return search(board, rowSeen, colSeen, blockSeen, r, c + 1);
    }

    for (int digit = 1; digit <= 9; ++digit) {
      if (fill(board, rowSeen, colSeen, blockSeen, r, c, digit)) {
        if (search(board, rowSeen, colSeen, blockSeen, r, c + 1)) {
          return true;
        }

        unfill(board, rowSeen, colSeen, blockSeen, r, c);
      }
    }

    return false;
  }
}
