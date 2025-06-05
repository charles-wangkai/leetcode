import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minMoves(String[] classroom, int energy) {
    int m = classroom.length;
    int n = classroom[0].length();

    Point start = null;
    int[][] litterIndices = new int[m][n];
    for (int r = 0; r < m; ++r) {
      Arrays.fill(litterIndices[r], -1);
    }
    int litterCount = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (classroom[r].charAt(c) == 'S') {
          start = new Point(r, c);
        } else if (classroom[r].charAt(c) == 'L') {
          litterIndices[r][c] = litterCount;
          ++litterCount;
        }
      }
    }

    int[][][][] distances = new int[m][n][energy + 1][1 << litterCount];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int e = 0; e <= energy; ++e) {
          Arrays.fill(distances[r][c][e], Integer.MAX_VALUE);
        }
      }
    }
    distances[start.r()][start.c()][energy][0] = 0;

    Queue<State> queue = new ArrayDeque<>();
    queue.offer(new State(start.r(), start.c(), energy, 0));

    while (!queue.isEmpty()) {
      State head = queue.poll();
      if (head.e() != 0) {
        for (int i = 0; i < R_OFFSETS.length; ++i) {
          int nextR = head.r() + R_OFFSETS[i];
          int nextC = head.c() + C_OFFSETS[i];
          if (nextR >= 0
              && nextR < m
              && nextC >= 0
              && nextC < n
              && classroom[nextR].charAt(nextC) != 'X') {
            int nextE = (classroom[nextR].charAt(nextC) == 'R') ? energy : (head.e() - 1);
            int nextMask =
                (litterIndices[nextR][nextC] == -1)
                    ? head.mask()
                    : (head.mask() | (1 << litterIndices[nextR][nextC]));
            if (distances[nextR][nextC][nextE][nextMask] == Integer.MAX_VALUE) {
              distances[nextR][nextC][nextE][nextMask] =
                  distances[head.r()][head.c()][head.e()][head.mask()] + 1;
              queue.offer(new State(nextR, nextC, nextE, nextMask));
            }
          }
        }
      }
    }

    int result = Integer.MAX_VALUE;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int e = 0; e <= energy; ++e) {
          result = Math.min(result, distances[r][c][e][(1 << litterCount) - 1]);
        }
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}

record Point(int r, int c) {}

record State(int r, int c, int e, int mask) {}
