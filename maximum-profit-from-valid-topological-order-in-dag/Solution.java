import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  public int maxProfit(int n, int[][] edges, int[] score) {
    int[] requirements = new int[n];
    for (int[] edge : edges) {
      requirements[edge[1]] |= 1 << edge[0];
    }

    int[] maxProfits = new int[1 << n];
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    while (!queue.isEmpty()) {
      int mask = queue.poll();
      for (int node = 0; node < n; ++node) {
        if (((mask >> node) & 1) == 0 && (mask & requirements[node]) == requirements[node]) {
          int nextMask = mask + (1 << node);
          if (maxProfits[nextMask] == 0) {
            queue.offer(nextMask);
          }

          maxProfits[nextMask] =
              Math.max(
                  maxProfits[nextMask],
                  maxProfits[mask] + (Integer.bitCount(mask) + 1) * score[node]);
        }
      }
    }

    return maxProfits[maxProfits.length - 1];
  }
}