import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[][] findFarmland(int[][] land) {
    int m = land.length;
    int n = land[0].length;

    List<int[]> result = new ArrayList<>();
    for (int r1 = 0; r1 < m; ++r1) {
      for (int c1 = 0; c1 < n; ++c1) {
        if (land[r1][c1] == 1) {
          int r2 = r1;
          while (r2 != m - 1 && land[r2 + 1][c1] == 1) {
            ++r2;
          }

          int c2 = c1;
          while (c2 != n - 1 && land[r2][c2 + 1] == 1) {
            ++c2;
          }

          for (int r = r1; r <= r2; ++r) {
            for (int c = c1; c <= c2; ++c) {
              land[r][c] = 0;
            }
          }

          result.add(new int[] {r1, c1, r2, c2});
        }
      }
    }

    return result.stream().toArray(int[][]::new);
  }
}
