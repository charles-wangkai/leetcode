import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int[][] findFarmland(int[][] land) {
    int m = land.length;
    int n = land[0].length;

    List<int[]> result = new ArrayList<>();
    boolean[][] visited = new boolean[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (!visited[r][c] && land[r][c] == 1) {
          int[] farmland = {
            Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE
          };
          search(farmland, land, visited, r, c);

          result.add(farmland);
        }
      }
    }

    return result.stream().toArray(int[][]::new);
  }

  void search(int[] farmland, int[][] land, boolean[][] visited, int r, int c) {
    int m = land.length;
    int n = land[0].length;

    visited[r][c] = true;

    farmland[0] = Math.min(farmland[0], r);
    farmland[1] = Math.min(farmland[1], c);
    farmland[2] = Math.max(farmland[2], r);
    farmland[3] = Math.max(farmland[3], c);

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < m
          && adjC >= 0
          && adjC < n
          && !visited[adjR][adjC]
          && land[adjR][adjC] == 1) {
        search(farmland, land, visited, adjR, adjC);
      }
    }
  }
}
