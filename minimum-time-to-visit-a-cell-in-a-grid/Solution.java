import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minimumTime(int[][] grid) {
    if (grid[0][1] >= 2 && grid[1][0] >= 2) {
      return -1;
    }

    int m = grid.length;
    int n = grid[0].length;

    int[][] distances = new int[m][n];
    for (int r = 0; r < m; ++r) {
      Arrays.fill(distances[r], Integer.MAX_VALUE);
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(0, 0, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.r()][head.c()] == Integer.MAX_VALUE) {
        distances[head.r()][head.c()] = head.distance();

        for (int i = 0; i < R_OFFSETS.length; ++i) {
          int adjR = head.r() + R_OFFSETS[i];
          int adjC = head.c() + C_OFFSETS[i];
          if (adjR >= 0
              && adjR < m
              && adjC >= 0
              && adjC < n
              && distances[adjR][adjC] == Integer.MAX_VALUE) {
            pq.offer(new Element(adjR, adjC, computeMinTime(head.distance(), grid[adjR][adjC])));
          }
        }
      }
    }

    return (distances[m - 1][n - 1] == Integer.MAX_VALUE) ? -1 : distances[m - 1][n - 1];
  }

  int computeMinTime(int fromTime, int requiredTime) {
    return (fromTime + 1 >= requiredTime)
        ? (fromTime + 1)
        : (requiredTime + ((fromTime % 2 == requiredTime % 2) ? 1 : 0));
  }
}

record Element(int r, int c, int distance) {}
