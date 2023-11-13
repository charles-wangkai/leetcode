import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minimumSeconds(List<List<String>> land) {
    int n = land.size();
    int m = land.get(0).size();

    int[][] floodedDistances = computeFloodedDistances(land);

    int[][] distances = new int[n][m];
    for (int r = 0; r < distances.length; ++r) {
      Arrays.fill(distances[r], Integer.MAX_VALUE);
    }

    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        if (land.get(r).get(c).equals("S")) {
          distances[r][c] = 0;
          queue.offer(new Point(r, c));
        }
      }
    }
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      if (land.get(head.r()).get(head.c()).equals("D")) {
        return distances[head.r()][head.c()];
      }

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < n
            && adjC >= 0
            && adjC < m
            && distances[adjR][adjC] == Integer.MAX_VALUE
            && !land.get(adjR).get(adjC).equals("X")
            && distances[head.r()][head.c()] + 1 < floodedDistances[adjR][adjC]) {
          distances[adjR][adjC] = distances[head.r()][head.c()] + 1;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return -1;
  }

  int[][] computeFloodedDistances(List<List<String>> land) {
    int n = land.size();
    int m = land.get(0).size();

    int[][] floodedDistances = new int[n][m];
    for (int r = 0; r < floodedDistances.length; ++r) {
      Arrays.fill(floodedDistances[r], Integer.MAX_VALUE);
    }

    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        if (land.get(r).get(c).equals("*")) {
          floodedDistances[r][c] = 0;
          queue.offer(new Point(r, c));
        }
      }
    }
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < n
            && adjC >= 0
            && adjC < m
            && floodedDistances[adjR][adjC] == Integer.MAX_VALUE
            && !land.get(adjR).get(adjC).equals("X")
            && !land.get(adjR).get(adjC).equals("D")) {
          floodedDistances[adjR][adjC] = floodedDistances[head.r()][head.c()] + 1;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return floodedDistances;
  }
}

record Point(int r, int c) {}
