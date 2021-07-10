import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int nearestExit(char[][] maze, int[] entrance) {
    int m = maze.length;
    int n = maze[0].length;

    int[][] distances = new int[m][n];
    for (int r = 0; r < distances.length; ++r) {
      Arrays.fill(distances[r], -1);
    }

    distances[entrance[0]][entrance[1]] = 0;
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(entrance[0], entrance[1]));

    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < m
            && adjC >= 0
            && adjC < n
            && distances[adjR][adjC] == -1
            && maze[adjR][adjC] == '.') {
          if (adjR == 0 || adjR == m - 1 || adjC == 0 || adjC == n - 1) {
            return distances[head.r][head.c] + 1;
          }

          distances[adjR][adjC] = distances[head.r][head.c] + 1;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return -1;
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
