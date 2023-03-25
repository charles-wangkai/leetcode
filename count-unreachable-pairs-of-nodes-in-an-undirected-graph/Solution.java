import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public long countPairs(int n, int[][] edges) {
    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    for (int[] edge : edges) {
      int root1 = findRoot(parents, edge[0]);
      int root2 = findRoot(parents, edge[1]);
      if (root1 != root2) {
        parents[root2] = root1;
      }
    }

    Map<Integer, Integer> rootToCount = new HashMap<>();
    for (int i = 0; i < n; ++i) {
      int root = findRoot(parents, i);
      rootToCount.put(root, rootToCount.getOrDefault(root, 0) + 1);
    }

    return rootToCount.values().stream().mapToLong(count -> (long) count * (n - count)).sum() / 2;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}
