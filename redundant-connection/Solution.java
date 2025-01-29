import java.util.Arrays;

class Solution {
  public int[] findRedundantConnection(int[][] edges) {
    int[] parents = new int[edges.length];
    Arrays.fill(parents, -1);

    for (int i = 0; ; ++i) {
      int root1 = findRoot(parents, edges[i][0] - 1);
      int root2 = findRoot(parents, edges[i][1] - 1);
      if (root1 == root2) {
        return edges[i];
      }

      parents[root2] = root1;
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
