import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minTimeToReach(int[][] moveTime) {
    int n = moveTime.length;
    int m = moveTime[0].length;

    int[][] times = new int[n][m];
    for (int r = 0; r < times.length; ++r) {
      Arrays.fill(times[r], -1);
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::time));
    pq.offer(new Element(0, 0, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (times[head.r()][head.c()] == -1) {
        times[head.r()][head.c()] = head.time();

        for (int i = 0; i < R_OFFSETS.length; ++i) {
          int adjR = head.r() + R_OFFSETS[i];
          int adjC = head.c() + C_OFFSETS[i];
          if (adjR >= 0 && adjR < n && adjC >= 0 && adjC < m && times[adjR][adjC] == -1) {
            pq.offer(new Element(adjR, adjC, Math.max(head.time(), moveTime[adjR][adjC]) + 1));
          }
        }
      }
    }

    return times[n - 1][m - 1];
  }
}

record Element(int r, int c, int time) {}
