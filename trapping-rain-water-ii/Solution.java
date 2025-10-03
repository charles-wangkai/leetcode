import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int trapRainWater(int[][] heightMap) {
    int m = heightMap.length;
    int n = heightMap[0].length;

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::level));
    int[][] levels = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        levels[r][c] = Integer.MAX_VALUE;

        if (r == 0 || r == m - 1 || c == 0 || c == n - 1) {
          pq.offer(new Element(r, c, heightMap[r][c]));
        }
      }
    }

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (levels[head.r()][head.c()] > head.level()) {
        levels[head.r()][head.c()] = head.level();

        for (int i = 0; i < R_OFFSETS.length; ++i) {
          int adjR = head.r() + R_OFFSETS[i];
          int adjC = head.c() + C_OFFSETS[i];
          if (adjR >= 0
              && adjR < m
              && adjC >= 0
              && adjC < n
              && levels[adjR][adjC] > Math.max(heightMap[adjR][adjC], levels[head.r()][head.c()])) {
            pq.offer(
                new Element(
                    adjR, adjC, Math.max(heightMap[adjR][adjC], levels[head.r()][head.c()])));
          }
        }
      }
    }

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        result += levels[r][c] - heightMap[r][c];
      }
    }

    return result;
  }
}

record Element(int r, int c, int level) {}
