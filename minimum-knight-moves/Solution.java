import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int LIMIT = 302;
  static final int SIZE = LIMIT * 2 + 1;
  static final int[] R_OFFSETS = {-2, -1, 1, 2, 2, 1, -1, -2};
  static final int[] C_OFFSETS = {1, 2, 2, 1, -1, -2, -2, -1};

  public int minKnightMoves(int x, int y) {
    int initialR = convert(0);
    int initialC = convert(0);
    int targetR = convert(x);
    int targetC = convert(y);

    int[][] distances = new int[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      Arrays.fill(distances[i], -1);
    }
    distances[initialR][initialC] = 0;

    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(initialR, initialC));

    while (true) {
      Point head = queue.poll();
      if (head.r() == targetR && head.c() == targetC) {
        return distances[head.r()][head.c()];
      }

      for (int i = 0; i < R_OFFSETS.length; i++) {
        int nextR = head.r() + R_OFFSETS[i];
        int nextC = head.c() + C_OFFSETS[i];
        if (nextR >= 0
            && nextR < SIZE
            && nextC >= 0
            && nextC < SIZE
            && distances[nextR][nextC] == -1) {
          distances[nextR][nextC] = distances[head.r()][head.c()] + 1;
          queue.offer(new Point(nextR, nextC));
        }
      }
    }
  }

  static int convert(int a) {
    return a + LIMIT;
  }
}

record Point(int r, int c) {}
