import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public int[] getBiggestThree(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    SortedSet<Integer> sums = new TreeSet<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int size = 1; ; ++size) {
          int sum = computeSum(grid, r, c, size);
          if (sum == -1) {
            break;
          }

          sums.add(sum);
        }
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < 3; ++i) {
      if (!sums.isEmpty()) {
        int max = sums.last();
        result.add(max);
        sums.remove(max);
      }
    }

    return result.stream().mapToInt(x -> x).toArray();
  }

  int computeSum(int[][] grid, int topR, int topC, int size) {
    if (size == 1) {
      return grid[topR][topC];
    }

    int m = grid.length;
    int n = grid[0].length;

    int rightR = topR + size - 1;
    int rightC = topC + size - 1;

    int downR = rightR + size - 1;
    int downC = topC;

    int leftR = topR + size - 1;
    int leftC = topC - size + 1;

    if (rightC >= n || downR >= m || leftC < 0) {
      return -1;
    }

    return IntStream.range(0, size - 1)
        .map(
            i ->
                grid[topR + i][topC + i]
                    + grid[rightR + i][rightC - i]
                    + grid[downR - i][downC - i]
                    + grid[leftR - i][leftC + i])
        .sum();
  }
}
