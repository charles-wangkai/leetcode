import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public int minOperations(int[][] grid, int x) {
    int m = grid.length;
    int n = grid[0].length;

    List<Integer> elements = new ArrayList<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        elements.add(grid[r][c]);
      }
    }
    Collections.sort(elements);

    int result = 0;
    for (int i = 0, j = elements.size() - 1; i < j; ++i, --j) {
      int diff = elements.get(j) - elements.get(i);
      if (diff % x != 0) {
        return -1;
      }

      result += diff / x;
    }

    return result;
  }
}
