import java.util.Arrays;

class Solution {
  public int minOperations(int[][] grid, int x) {
    int[] values = Arrays.stream(grid).flatMapToInt(Arrays::stream).sorted().toArray();
    if (Arrays.stream(values).map(value -> value % x).distinct().count() != 1) {
      return -1;
    }

    int result = 0;
    for (int i = 0, j = values.length - 1; i < j; ++i, --j) {
      result += (values[j] - values[i]) / x;
    }

    return result;
  }
}
