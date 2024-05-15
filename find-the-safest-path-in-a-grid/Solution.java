import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int maximumSafenessFactor(List<List<Integer>> grid) {
    int n = grid.size();

    int[][] distances = buildDistances(grid);

    int result = -1;
    int lower = 0;
    int upper = grid.size();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (search(distances, middle, new boolean[n][n], 0, 0)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean search(int[][] distances, int safeness, boolean[][] visited, int r, int c) {
    int n = distances.length;

    visited[r][c] = true;

    if (distances[r][c] < safeness) {
      return false;
    }
    if (r == n - 1 && c == n - 1) {
      return true;
    }

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < n
          && adjC >= 0
          && adjC < n
          && !visited[adjR][adjC]
          && search(distances, safeness, visited, adjR, adjC)) {
        return true;
      }
    }

    return false;
  }

  int[][] buildDistances(List<List<Integer>> grid) {
    int n = grid.size();

    int[][] distances = new int[n][n];
    for (int r = 0; r < distances.length; ++r) {
      Arrays.fill(distances[r], -1);
    }

    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid.get(r).get(c) == 1) {
          distances[r][c] = 0;
          queue.offer(new Point(r, c));
        }
      }
    }
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0 && adjR < n && adjC >= 0 && adjC < n && distances[adjR][adjC] == -1) {
          distances[adjR][adjC] = distances[head.r()][head.c()] + 1;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return distances;
  }
}

record Point(int r, int c) {}
