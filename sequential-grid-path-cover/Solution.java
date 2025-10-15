import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public List<List<Integer>> findPath(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        List<List<Integer>> result = search(grid, new ArrayList<>(), 0, 1, r, c);
        if (result != null) {
          return result;
        }
      }
    }

    return List.of();
  }

  List<List<Integer>> search(
      int[][] grid, List<List<Integer>> path, int mask, int value, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;

    int index = r * n + c;
    if (r >= 0
        && r < m
        && c >= 0
        && c < n
        && ((mask >> index) & 1) == 0
        && (grid[r][c] == 0 || grid[r][c] == value)) {
      path.add(List.of(r, c));

      mask |= (1 << index);
      if (mask == (1 << (m * n)) - 1) {
        return path;
      }

      if (grid[r][c] == value) {
        ++value;
      }

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = r + R_OFFSETS[i];
        int adjC = c + C_OFFSETS[i];

        List<List<Integer>> result = search(grid, path, mask, value, adjR, adjC);
        if (result != null) {
          return result;
        }
      }

      path.removeLast();
    }

    return null;
  }
}