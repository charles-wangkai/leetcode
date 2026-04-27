import java.util.List;
import java.util.Set;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};
  static final List<Set<Integer>> CURRENT_VALID_STREETS =
      List.of(Set.of(2, 5, 6), Set.of(1, 4, 6), Set.of(2, 3, 4), Set.of(1, 3, 5));
  static final List<Set<Integer>> ADJ_VALID_STREETS =
      List.of(Set.of(2, 3, 4), Set.of(1, 3, 5), Set.of(2, 5, 6), Set.of(1, 4, 6));

  public boolean hasValidPath(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    return search(grid, new boolean[m][n], 0, 0);
  }

  boolean search(int[][] grid, boolean[][] visited, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;

    if (r == m - 1 && c == n - 1) {
      return true;
    }

    visited[r][c] = true;

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < m
          && adjC >= 0
          && adjC < n
          && !visited[adjR][adjC]
          && CURRENT_VALID_STREETS.get(i).contains(grid[r][c])
          && ADJ_VALID_STREETS.get(i).contains(grid[adjR][adjC])
          && search(grid, visited, adjR, adjC)) {
        return true;
      }
    }

    return false;
  }
}