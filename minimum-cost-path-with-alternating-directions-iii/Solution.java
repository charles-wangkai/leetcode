import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public long minCost(int m, int n, int[][] penalty) {
    long[][][] costs = new long[m][n][2];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        Arrays.fill(costs[r][c], Long.MAX_VALUE);
      }
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::cost));
    pq.offer(new Element(0, 0, 0, 1));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (costs[head.r()][head.c()][head.parity()] == Long.MAX_VALUE) {
        costs[head.r()][head.c()][head.parity()] = head.cost();

        for (int i = 0; i < R_OFFSETS.length; ++i) {
          int adjR = head.r() + R_OFFSETS[i];
          int adjC = head.c() + C_OFFSETS[i];
          if (adjR >= 0
              && adjR < m
              && adjC >= 0
              && adjC < n
              && costs[adjR][adjC][1 - head.parity()] == Long.MAX_VALUE) {
            if ((head.parity() == 0 && (i == 1 || i == 2))
                || (head.parity() == 1 && (i == 0 || i == 3))) {
              pq.offer(
                  new Element(
                      adjR, adjC, 1 - head.parity(), head.cost() + (adjR + 1) * (adjC + 1)));
            } else {
              pq.offer(
                  new Element(
                      adjR,
                      adjC,
                      1 - head.parity(),
                      head.cost() + (adjR + 1) * (adjC + 1) + penalty[head.r()][head.c()]));
            }
          }
        }

        if (costs[head.r()][head.c()][1 - head.parity()] == Long.MAX_VALUE) {
          pq.offer(
              new Element(
                  head.r(),
                  head.c(),
                  1 - head.parity(),
                  head.cost() + penalty[head.r()][head.c()]));
        }
      }
    }

    return Math.min(costs[m - 1][n - 1][0], costs[m - 1][n - 1][1]);
  }
}

record Element(int r, int c, int parity, long cost) {}
