import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minCost(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int[][][][] costs = new int[m][n][4][k + 1];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int direction = 0; direction < R_OFFSETS.length; ++direction) {
          Arrays.fill(costs[r][c][direction], Integer.MAX_VALUE);
        }
      }
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::cost));
    for (int direction = 0; direction < R_OFFSETS.length; ++direction) {
      pq.offer(new Element(0, 0, direction, k, grid[0][0]));
    }
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (head.r() == m - 1 && head.c() == n - 1) {
        return head.cost();
      }

      if (costs[head.r()][head.c()][head.direction()][head.turnRest()] == Integer.MAX_VALUE) {
        costs[head.r()][head.c()][head.direction()][head.turnRest()] = head.cost();

        for (int nextDirection = 0; nextDirection < R_OFFSETS.length; ++nextDirection) {
          int nextR = head.r() + R_OFFSETS[nextDirection];
          int nextC = head.c() + C_OFFSETS[nextDirection];
          int nextTurnRest = head.turnRest() - ((nextDirection == head.direction()) ? 0 : 1);
          if (nextR >= 0
              && nextR < m
              && nextC >= 0
              && nextC < n
              && nextTurnRest != -1
              && costs[nextR][nextC][nextDirection][nextTurnRest] == Integer.MAX_VALUE) {
            pq.offer(
                new Element(
                    nextR, nextC, nextDirection, nextTurnRest, head.cost() + grid[nextR][nextC]));
          }
        }
      }
    }

    return -1;
  }
}

record Element(int r, int c, int direction, int turnRest, int cost) {}
