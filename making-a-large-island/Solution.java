import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int largestIsland(int[][] grid) {
    int n = grid.length;

    boolean[][] visited = new boolean[n][n];
    int[][] marks = new int[n][n];
    int[][] areas = new int[n][n];

    int mark = 1;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (!visited[r][c] && grid[r][c] == 1) {
          fill(grid, visited, marks, areas, mark, r, c);

          ++mark;
        }
      }
    }

    int result = 0;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1) {
          result = Math.max(result, areas[r][c]);
        } else {
          int totalArea = 1;
          Set<Integer> usedMarks = new HashSet<>();
          for (int i = 0; i < R_OFFSETS.length; ++i) {
            int adjR = r + R_OFFSETS[i];
            int adjC = c + C_OFFSETS[i];

            if (adjR >= 0
                && adjR < n
                && adjC >= 0
                && adjC < n
                && !usedMarks.contains(marks[adjR][adjC])) {
              usedMarks.add(marks[adjR][adjC]);
              totalArea += areas[adjR][adjC];
            }
          }

          result = Math.max(result, totalArea);
        }
      }
    }

    return result;
  }

  void fill(
      int[][] grid,
      boolean[][] visited,
      int[][] marks,
      int[][] areas,
      int mark,
      int beginR,
      int beginC) {
    List<Point> island = new ArrayList<>();
    search(grid, visited, island, beginR, beginC);

    for (Point point : island) {
      marks[point.r][point.c] = mark;
      areas[point.r][point.c] = island.size();
    }
  }

  void search(int[][] grid, boolean[][] visited, List<Point> island, int r, int c) {
    int n = grid.length;

    visited[r][c] = true;
    island.add(new Point(r, c));

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];

      if (adjR >= 0
          && adjR < n
          && adjC >= 0
          && adjC < n
          && grid[adjR][adjC] == 1
          && !visited[adjR][adjC]) {
        search(grid, visited, island, adjR, adjC);
      }
    }
  }
}

class Point {
  int r;
  int c;

  Point(int r, int c) {
    this.r = r;
    this.c = c;
  }
}
