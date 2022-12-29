import java.util.Arrays;

class Solution {
  static final int LIMIT = 1_000_000;

  public int matrixMedian(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int result = -1;
    int lower = 1;
    int upper = LIMIT;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (Arrays.stream(grid).mapToInt(a -> computeLessEqualNum(a, middle)).sum()
          >= (m * n + 1) / 2) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  int computeLessEqualNum(int[] a, int target) {
    int index = -1;
    int lower = 0;
    int upper = a.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (a[middle] <= target) {
        index = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return index + 1;
  }
}
