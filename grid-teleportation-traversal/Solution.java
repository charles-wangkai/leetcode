import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minMoves(String[] matrix) {
    int m = matrix.length;
    int n = matrix[0].length();

    Map<Character, List<Point>> letterToPoints = new HashMap<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (matrix[r].charAt(c) != '.' && matrix[r].charAt(c) != '#') {
          letterToPoints.putIfAbsent(matrix[r].charAt(c), new ArrayList<>());
          letterToPoints.get(matrix[r].charAt(c)).add(new Point(r, c));
        }
      }
    }

    int[][] distances = new int[m][n];
    for (int r = 0; r < distances.length; ++r) {
      Arrays.fill(distances[r], -1);
    }

    Queue<Point> queue = new ArrayDeque<>();
    process(matrix, letterToPoints, distances, queue, 0, 0, 0);
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < m
            && adjC >= 0
            && adjC < n
            && matrix[adjR].charAt(adjC) != '#'
            && distances[adjR][adjC] == -1) {
          process(
              matrix,
              letterToPoints,
              distances,
              queue,
              adjR,
              adjC,
              distances[head.r()][head.c()] + 1);
        }
      }
    }

    return distances[m - 1][n - 1];
  }

  void process(
      String[] matrix,
      Map<Character, List<Point>> letterToPoints,
      int[][] distances,
      Queue<Point> queue,
      int r,
      int c,
      int distance) {
    for (Point point : letterToPoints.getOrDefault(matrix[r].charAt(c), List.of(new Point(r, c)))) {
      if (distances[point.r()][point.c()] == -1) {
        distances[point.r()][point.c()] = distance;
        queue.offer(new Point(point.r(), point.c()));
      }
    }
  }
}

record Point(int r, int c) {}
