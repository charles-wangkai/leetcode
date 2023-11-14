import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public long sumRemoteness(int[][] grid) {
    int n = grid.length;

    int[][] groups = new int[n][n];
    for (int r = 0; r < groups.length; ++r) {
      Arrays.fill(groups[r], -1);
    }

    List<Long> groupSums = new ArrayList<>();
    int group = 0;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] != -1 && groups[r][c] == -1) {
          groupSums.add(fill(grid, groups, group, r, c));
          ++group;
        }
      }
    }

    long total = groupSums.stream().mapToLong(Long::longValue).sum();

    long result = 0;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] != -1) {
          result += total - groupSums.get(groups[r][c]);
        }
      }
    }

    return result;
  }

  long fill(int[][] grid, int[][] groups, int group, int r, int c) {
    int n = grid.length;

    groups[r][c] = group;

    long result = grid[r][c];
    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < n
          && adjC >= 0
          && adjC < n
          && grid[adjR][adjC] != -1
          && groups[adjR][adjC] == -1) {
        result += fill(grid, groups, group, adjR, adjC);
      }
    }

    return result;
  }
}
