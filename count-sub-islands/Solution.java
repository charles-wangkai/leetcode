import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int countSubIslands(int[][] grid1, int[][] grid2) {
    int m = grid1.length;
    int n = grid1[0].length;

    int result = 0;
    boolean[][] visited = new boolean[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (!visited[r][c] && grid2[r][c] == 1) {
          List<Point> island = new ArrayList<>();
          search(island, grid2, visited, r, c);

          if (island.stream().allMatch(p -> grid1[p.r()][p.c()] == 1)) {
            ++result;
          }
        }
      }
    }

    return result;
  }

  void search(List<Point> island, int[][] grid2, boolean[][] visited, int r, int c) {
    int m = grid2.length;
    int n = grid2[0].length;

    island.add(new Point(r, c));
    visited[r][c] = true;

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < m
          && adjC >= 0
          && adjC < n
          && !visited[adjR][adjC]
          && grid2[adjR][adjC] == 1) {
        search(island, grid2, visited, adjR, adjC);
      }
    }
  }
}

record Point(int r, int c) {}
