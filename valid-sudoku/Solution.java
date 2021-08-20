import java.util.stream.IntStream;

class Solution {
  public boolean isValidSudoku(char[][] board) {
    return IntStream.range(0, 9).allMatch(r -> check(board, r, 0, 1, 9))
        && IntStream.range(0, 9).allMatch(c -> check(board, 0, c, 9, 1))
        && IntStream.range(0, 3)
            .allMatch(i -> IntStream.range(0, 3).allMatch(j -> check(board, i * 3, j * 3, 3, 3)));
  }

  boolean check(char[][] board, int r, int c, int row, int col) {
    boolean[] used = new boolean[10];
    for (int dr = 0; dr < row; ++dr) {
      for (int dc = 0; dc < col; ++dc) {
        char cell = board[r + dr][c + dc];
        if (cell != '.') {
          int digit = cell - '0';
          if (used[digit]) {
            return false;
          }

          used[digit] = true;
        }
      }
    }

    return true;
  }
}
