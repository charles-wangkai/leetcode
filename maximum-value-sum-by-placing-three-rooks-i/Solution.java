import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maximumValueSum(int[][] board) {
    int m = board.length;
    int n = board[0].length;

    int[][] sortedColumns = new int[n][];
    for (int c = 0; c < sortedColumns.length; ++c) {
      int c_ = c;
      sortedColumns[c] =
          IntStream.range(0, m)
              .map(r -> board[r][c_])
              .boxed()
              .sorted(Comparator.reverseOrder())
              .mapToInt(Integer::intValue)
              .toArray();
    }

    long result = Long.MIN_VALUE;
    for (int r1 = 0; r1 < m; ++r1) {
      for (int r2 = 0; r2 < m; ++r2) {
        if (r2 != r1) {
          int r1_ = r1;
          int r2_ = r2;
          result =
              Math.max(
                  result,
                  computeMaxSum(
                      board[r1],
                      board[r2],
                      IntStream.range(0, n)
                          .map(c -> findMaxValue(sortedColumns[c], board[r1_][c], board[r2_][c]))
                          .toArray()));
        }
      }
    }

    return result;
  }

  int findMaxValue(int[] sortedColumn, int excluded1, int excluded2) {
    if (excluded1 < excluded2) {
      return findMaxValue(sortedColumn, excluded2, excluded1);
    }

    if (sortedColumn[0] != excluded1) {
      return sortedColumn[0];
    }
    if (sortedColumn[1] != excluded2) {
      return sortedColumn[1];
    }

    return sortedColumn[2];
  }

  long computeMaxSum(int[] values1, int[] values2, int[] values3) {
    int length = values1.length;

    int[] leftMaxs = new int[length];
    for (int i = 0; i < leftMaxs.length; ++i) {
      leftMaxs[i] = Math.max((i == 0) ? Integer.MIN_VALUE : leftMaxs[i - 1], values1[i]);
    }

    int[] rightMaxs = new int[length];
    for (int i = rightMaxs.length - 1; i >= 0; --i) {
      rightMaxs[i] =
          Math.max((i == rightMaxs.length - 1) ? Integer.MIN_VALUE : rightMaxs[i + 1], values3[i]);
    }

    return IntStream.rangeClosed(1, length - 2)
        .mapToLong(i -> (long) leftMaxs[i - 1] + values2[i] + rightMaxs[i + 1])
        .max()
        .getAsLong();
  }
}