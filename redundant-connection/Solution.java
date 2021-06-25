import java.util.Arrays;

class Solution {
  public int[] findRedundantConnection(int[][] edges) {
    for (int i = edges.length - 1; ; --i) {
      if (isTree(edges, i)) {
        return edges[i];
      }
    }
  }

  boolean isTree(int[][] edges, int redundantIndex) {
    int n = edges.length;

    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    for (int i = 0; i < edges.length; ++i) {
      if (i != redundantIndex) {
        int root0 = findRoot(parents, edges[i][0] - 1);
        int root1 = findRoot(parents, edges[i][1] - 1);
        if (root0 == root1) {
          return false;
        }

        parents[root1] = root0;
      }
    }

    return true;
  }

  int findRoot(int[] parents, int node) {
    int root = node;
    while (parents[root] != -1) {
      root = parents[root];
    }

    int p = node;
    while (p != root) {
      int next = parents[p];
      parents[p] = root;

      p = next;
    }

    return root;
  }
}
