import java.util.Arrays;

class Solution {
  public int[] minimumCost(int n, int[][] edges, int[][] query) {
    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    int[] subtreeAnds = new int[n];
    Arrays.fill(subtreeAnds, -1);

    for (int[] edge : edges) {
      int root1 = findRoot(parents, edge[0]);
      int root2 = findRoot(parents, edge[1]);
      if (root1 != root2) {
        parents[root2] = root1;
        subtreeAnds[root1] = and(subtreeAnds[root1], and(subtreeAnds[root2], edge[2]));
      } else {
        subtreeAnds[root1] = and(subtreeAnds[root1], edge[2]);
      }
    }

    return Arrays.stream(query)
        .mapToInt(
            q -> {
              if (q[0] == q[1]) {
                return 0;
              }

              int root1 = findRoot(parents, q[0]);
              int root2 = findRoot(parents, q[1]);

              return (root1 == root2) ? subtreeAnds[root1] : -1;
            })
        .toArray();
  }

  int and(int x, int y) {
    if (x == -1) {
      return y;
    }
    if (y == -1) {
      return x;
    }

    return x & y;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}