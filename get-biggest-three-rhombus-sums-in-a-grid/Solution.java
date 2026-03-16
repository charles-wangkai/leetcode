import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] getBiggestThree(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    List<Integer> sums = new ArrayList<>();
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

    return sums.stream()
        .distinct()
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .mapToInt(Integer::intValue)
        .toArray();
  }

  int computeSum(int[][] grid, int topR, int topC, int size) {
    int m = grid.length;
    int n = grid[0].length;

    if (size == 1) {
      return grid[topR][topC];
    }

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
