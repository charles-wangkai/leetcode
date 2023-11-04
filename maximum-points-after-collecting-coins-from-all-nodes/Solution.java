import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int LIMIT = 14;

  public int maximumPoints(int[][] edges, int[] coins, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[coins.length];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[][] maxPoints = new int[coins.length][LIMIT + 1];
    for (int i = 0; i < maxPoints.length; ++i) {
      Arrays.fill(maxPoints[i], Integer.MIN_VALUE);
    }

    search(adjLists, coins, k, maxPoints, -1, 0);

    return maxPoints[0][0];
  }

  void search(
      List<Integer>[] adjLists, int[] coins, int k, int[][] maxPoints, int parent, int node) {
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search(adjLists, coins, k, maxPoints, node, adj);
      }
    }

    int coin = coins[node];
    for (int i = 0; i <= LIMIT; ++i) {
      int childrenSum1 = 0;
      int childrenSum2 = 0;
      for (int adj : adjLists[node]) {
        if (adj != parent) {
          childrenSum1 += maxPoints[adj][i];
          childrenSum2 += maxPoints[adj][Math.min(LIMIT, i + 1)];
        }
      }

      maxPoints[node][i] = Math.max(coin - k + childrenSum1, coin / 2 + childrenSum2);

      coin /= 2;
    }
  }
}
