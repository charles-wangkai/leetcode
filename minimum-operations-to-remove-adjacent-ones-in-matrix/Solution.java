// https://leetcode.com/problems/minimum-operations-to-remove-adjacent-ones-in-matrix/discuss/1663225/C%2B%2B-Hungarian-that-works-with-the-updated-test-cases

import java.util.Arrays;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minimumOperations(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] matchings = new int[m][n];
    for (int i = 0; i < matchings.length; ++i) {
      Arrays.fill(matchings[i], -1);
    }
    int[][] visited = new int[m][n];
    for (int i = 0; i < visited.length; ++i) {
      Arrays.fill(visited[i], -1);
    }

    int matchingCount = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if ((r + c) % 2 == 0
            && grid[r][c] == 1
            && matchings[r][c] == -1
            && search(grid, matchings, visited, r * n + c, r, c)) {
          ++matchingCount;
        }
      }
    }

    return matchingCount;
  }

  boolean search(int[][] grid, int[][] matchings, int[][] visited, int from, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < m
          && adjC >= 0
          && adjC < n
          && grid[adjR][adjC] == 1
          && visited[adjR][adjC] != from) {
        visited[adjR][adjC] = from;

        if (matchings[adjR][adjC] == -1
            || search(
                grid,
                matchings,
                visited,
                from,
                matchings[adjR][adjC] / n,
                matchings[adjR][adjC] % n)) {
          matchings[r][c] = adjR * n + adjC;
          matchings[adjR][adjC] = r * n + c;

          return true;
        }
      }
    }

    return false;
  }
}