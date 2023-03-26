import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
  public int collectTheCoins(int[] coins, int[][] edges) {
    int n = coins.length;

    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[n];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      adjSets[edge[0]].add(edge[1]);
      adjSets[edge[1]].add(edge[0]);
    }

    int restCount = n;
    int[] distances = new int[n];
    Arrays.fill(distances, -1);
    Queue<Integer> leaves = new ArrayDeque<>();
    for (int i = 0; i < adjSets.length; ++i) {
      if (adjSets[i].size() == 1) {
        leaves.offer(i);
      }
    }
    while (!leaves.isEmpty()) {
      int head = leaves.poll();
      if (adjSets[head].size() == 1 && distances[head] <= 1) {
        if (distances[head] == -1 && coins[head] == 1) {
          distances[head] = 0;
        }

        int adj = adjSets[head].iterator().next();
        if (distances[head] != -1) {
          distances[adj] = Math.max(distances[adj], distances[head] + 1);
        }
        adjSets[head].remove(adj);
        adjSets[adj].remove(head);
        if (adjSets[adj].size() == 1) {
          leaves.offer(adj);
        }

        --restCount;
      }
    }

    return (restCount - 1) * 2;
  }
}
