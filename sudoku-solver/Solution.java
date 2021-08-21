class Solution {
  static final int CELL_SIZE = 9;
  static final int BLOCK_SIZE = 3;

  public void solveSudoku(char[][] board) {
    boolean[][] rows = new boolean[CELL_SIZE][10];
    boolean[][] cols = new boolean[CELL_SIZE][10];
    boolean[][][] blocks = new boolean[BLOCK_SIZE][BLOCK_SIZE][10];
    for (int r = 0; r < CELL_SIZE; ++r) {
      for (int c = 0; c < CELL_SIZE; ++c) {
        if (board[r][c] != '.') {
          fill(board, rows, cols, blocks, r, c, board[r][c] - '0');
        }
      }
    }
    search(board, rows, cols, blocks, 0, 0);
  }

  boolean fill(
      char[][] board,
      boolean[][] rows,
      boolean[][] cols,
      boolean[][][] blocks,
      int r,
      int c,
      int digit) {
    if (rows[r][digit] || cols[c][digit] || blocks[r / BLOCK_SIZE][c / BLOCK_SIZE][digit]) {
      return false;
    }

    board[r][c] = (char) ('0' + digit);
    rows[r][digit] = true;
    cols[c][digit] = true;
    blocks[r / BLOCK_SIZE][c / BLOCK_SIZE][digit] = true;

    return true;
  }

  void unfill(
      char[][] board, boolean[][] rows, boolean[][] cols, boolean[][][] blocks, int r, int c) {
    int digit = board[r][c] - '0';
    board[r][c] = '.';
    rows[r][digit] = false;
    cols[c][digit] = false;
    blocks[r / BLOCK_SIZE][c / BLOCK_SIZE][digit] = false;
  }

  boolean search(
      char[][] board, boolean[][] rows, boolean[][] cols, boolean[][][] blocks, int r, int c) {
    if (r == CELL_SIZE) {
      return true;
    }
    if (c == CELL_SIZE) {
      return search(board, rows, cols, blocks, r + 1, 0);
    }
    if (board[r][c] != '.') {
      return search(board, rows, cols, blocks, r, c + 1);
    }

    for (int digit = 1; digit <= 9; ++digit) {
      if (fill(board, rows, cols, blocks, r, c, digit)) {
        if (search(board, rows, cols, blocks, r, c + 1)) {
          return true;
        }

        unfill(board, rows, cols, blocks, r, c);
      }
    }

    return false;
  }
}
