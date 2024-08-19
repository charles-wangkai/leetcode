import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maximumValueSum(int[][] board) {
    int m = board.length;
    int n = board[0].length;

    int[][] upMaxValues = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        upMaxValues[r][c] =
            Math.max((r == 0) ? Integer.MIN_VALUE : upMaxValues[r - 1][c], board[r][c]);
      }
    }

    int[][] downMaxValues = new int[m][n];
    for (int r = m - 1; r >= 0; --r) {
      for (int c = 0; c < n; ++c) {
        downMaxValues[r][c] =
            Math.max((r == m - 1) ? Integer.MIN_VALUE : downMaxValues[r + 1][c], board[r][c]);
      }
    }

    long result = Long.MIN_VALUE;
    for (int r = 1; r <= m - 2; ++r) {
      int[] upSortedIndices = buildSortedIndices(upMaxValues[r - 1]);
      int[] downSortedIndices = buildSortedIndices(downMaxValues[r + 1]);
      for (int c = 0; c < n; ++c) {
        for (int upIndex : upSortedIndices) {
          for (int downIndex : downSortedIndices) {
            if (c != upIndex && upIndex != downIndex && downIndex != c) {
              result =
                  Math.max(
                      result,
                      (long) board[r][c]
                          + upMaxValues[r - 1][upIndex]
                          + downMaxValues[r + 1][downIndex]);
            }
          }
        }
      }
    }

    return result;
  }

  int[] buildSortedIndices(int[] maxValues) {
    return IntStream.range(0, maxValues.length)
        .boxed()
        .sorted(Comparator.<Integer, Integer>comparing(c -> maxValues[c]).reversed())
        .limit(3)
        .mapToInt(Integer::intValue)
        .toArray();
  }
}