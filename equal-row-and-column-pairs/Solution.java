import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int equalPairs(int[][] grid) {
    int n = grid.length;

    int result = 0;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        int c_ = c;
        if (Arrays.equals(grid[r], IntStream.range(0, n).map(i -> grid[i][c_]).toArray())) {
          ++result;
        }
      }
    }

    return result;
  }
}
