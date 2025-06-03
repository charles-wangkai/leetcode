import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
  public int[][] minAbsDiff(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] result = new int[m - k + 1][n - k + 1];
    for (int beginR = 0; beginR < result.length; ++beginR) {
      NavigableMap<Integer, Integer> valueToCount = new TreeMap<>();
      for (int c = 0; c < k - 1; ++c) {
        for (int r = beginR; r < beginR + k; ++r) {
          updateMap(valueToCount, grid[r][c], 1);
        }
      }

      for (int beginC = 0; beginC < result[0].length; ++beginC) {
        for (int r = beginR; r < beginR + k; ++r) {
          updateMap(valueToCount, grid[r][beginC + k - 1], 1);
        }

        result[beginR][beginC] = computeMinDiff(valueToCount);

        for (int r = beginR; r < beginR + k; ++r) {
          updateMap(valueToCount, grid[r][beginC], -1);
        }
      }
    }

    return result;
  }

  int computeMinDiff(NavigableMap<Integer, Integer> valueToCount) {
    int[] values = valueToCount.keySet().stream().mapToInt(Integer::intValue).toArray();

    return (values.length == 1)
        ? 0
        : IntStream.range(0, values.length - 1)
            .map(i -> values[i + 1] - values[i])
            .min()
            .getAsInt();
  }

  void updateMap(NavigableMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
