import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public boolean isValidSudoku(char[][] board) {
    return IntStream.range(0, 9).allMatch(beginR -> check(board, 1, 9, beginR, 0))
        && IntStream.range(0, 9).allMatch(beginC -> check(board, 9, 1, 0, beginC))
        && IntStream.range(0, 3)
            .allMatch(i -> IntStream.range(0, 3).allMatch(j -> check(board, 3, 3, i * 3, j * 3)));
  }

  boolean check(char[][] board, int row, int col, int beginR, int beginC) {
    Set<Character> seen = new HashSet<>();
    for (int dr = 0; dr < row; ++dr) {
      for (int dc = 0; dc < col; ++dc) {
        char cell = board[beginR + dr][beginC + dc];
        if (cell != '.') {
          if (seen.contains(cell)) {
            return false;
          }

          seen.add(cell);
        }
      }
    }

    return true;
  }
}
