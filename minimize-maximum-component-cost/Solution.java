import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int minCost(int n, int[][] edges, int k) {
    if (k == n) {
      return 0;
    }

    Arrays.sort(edges, Comparator.comparing(edge -> edge[2]));

    int componentNum = n;
    int[] parents = new int[n];
    Arrays.fill(parents, -1);
    for (int i = 0; ; ++i) {
      int root1 = findRoot(parents, edges[i][0]);
      int root2 = findRoot(parents, edges[i][1]);
      if (root1 != root2) {
        parents[root2] = root1;
        --componentNum;
      }

      if (componentNum == k) {
        return edges[i][2];
      }
    }
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}