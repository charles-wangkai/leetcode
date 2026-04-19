import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int[][] colorGrid(int n, int m, int[][] sources) {
    Arrays.sort(sources, Comparator.<int[], Integer>comparing(source -> source[2]).reversed());

    int[][] result = new int[n][m];
    Queue<Point> queue = new ArrayDeque<>();
    for (int[] source : sources) {
      result[source[0]][source[1]] = source[2];
      queue.offer(new Point(source[0], source[1]));
    }

    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0 && adjR < n && adjC >= 0 && adjC < m && result[adjR][adjC] == 0) {
          result[adjR][adjC] = result[head.r()][head.c()];
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return result;
  }
}

record Point(int r, int c) {}
