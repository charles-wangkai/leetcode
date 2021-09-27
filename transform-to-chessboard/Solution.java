import java.util.stream.IntStream;

class Solution {
  public int movesToChessboard(int[][] board) {
    if (!isPossible(board)) {
      return -1;
    }

    int totalMove = 0;

    int rowMove = swapRows(board);
    if (rowMove == -1) {
      return -1;
    }
    totalMove += rowMove;

    transpose(board);

    rowMove = swapRows(board);
    if (rowMove == -1) {
      return -1;
    }
    totalMove += rowMove;

    return totalMove;
  }

  boolean isPossible(int[][] board) {
    int n = board.length;

    for (int r = 1; r < n; ++r) {
      boolean sameOrInvert = board[r][0] == board[0][0];

      int r_ = r;
      if (IntStream.range(0, n)
          .anyMatch(
              c ->
                  (sameOrInvert && board[r_][c] != board[0][c])
                      || (!sameOrInvert && board[r_][c] != 1 - board[0][c]))) {
        return false;
      }
    }

    return true;
  }

  void transpose(int[][] board) {
    int n = board.length;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < r; ++c) {
        int temp = board[r][c];
        board[r][c] = board[c][r];
        board[c][r] = temp;
      }
    }
  }

  int swapRows(int[][] board) {
    int n = board.length;

    int[] counts = new int[2];
    for (int r = 0; r < n; ++r) {
      ++counts[board[r][0]];
    }

    int minMove = Integer.MAX_VALUE;
    for (int startValue : new int[] {0, 1}) {
      int[] targetCounts = {n / 2, n / 2};
      if (n % 2 != 0) {
        ++targetCounts[startValue];
      }

      if (counts[0] != targetCounts[0] || counts[1] != targetCounts[1]) {
        continue;
      }

      int diff = 0;
      int targetValue = startValue;
      for (int r = 0; r < n; ++r) {
        if (board[r][0] != targetValue) {
          ++diff;
        }

        targetValue = 1 - targetValue;
      }

      minMove = Math.min(minMove, diff / 2);
    }

    return (minMove == Integer.MAX_VALUE) ? -1 : minMove;
  }
}
