import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
    int[] parents = new int[n + 1];
    Arrays.fill(parents, -1);

    for (int i = threshold + 1; i * 2 <= n; ++i) {
      for (int j = i; j + i <= n; j += i) {
        union(parents, j, j + i);
      }
    }

    return Arrays.stream(queries)
        .map(query -> findRoot(parents, query[0]) == findRoot(parents, query[1]))
        .collect(Collectors.toList());
  }

  void union(int[] parents, int node1, int node2) {
    int root1 = findRoot(parents, node1);
    int root2 = findRoot(parents, node2);
    if (root1 != root2) {
      parents[root2] = root1;
    }
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
