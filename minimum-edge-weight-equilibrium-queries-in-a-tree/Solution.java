import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int MAX_W = 26;

  public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
    Tree tree =
        new Tree(
            Arrays.stream(edges).mapToInt(edge -> edge[0]).toArray(),
            Arrays.stream(edges).mapToInt(edge -> edge[1]).toArray(),
            Arrays.stream(edges).mapToInt(edge -> edge[2]).toArray());

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int lca = tree.findLca(query[0], query[1]);
              int[] totalCounts =
                  IntStream.rangeClosed(0, MAX_W)
                      .map(
                          i ->
                              tree.weightCounts[query[0]][i]
                                  + tree.weightCounts[query[1]][i]
                                  - 2 * tree.weightCounts[lca][i])
                      .toArray();

              return Arrays.stream(totalCounts).sum() - Arrays.stream(totalCounts).max().getAsInt();
            })
        .toArray();
  }
}

class Tree {
  int n;
  int[] u;
  int[] v;
  int[] weights;
  List<Integer>[] edgeLists;
  int[] depths;
  int[][] ancestors;
  int[][] weightCounts;

  @SuppressWarnings("unchecked")
  Tree(int[] u, int[] v, int[] weights) {
    n = u.length + 1;

    this.u = u;
    this.v = v;
    this.weights = weights;

    edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < u.length; ++i) {
      edgeLists[u[i]].add(i);
      edgeLists[v[i]].add(i);
    }

    depths = new int[n];
    ancestors = new int[n][Integer.toBinaryString(n).length()];
    weightCounts = new int[n][Solution.MAX_W + 1];
    init(0, -1, -1, 0);
  }

  private void init(int depth, int parent, int weight, int node) {
    depths[node] = depth;

    ancestors[node][0] = parent;
    for (int i = 1; i < ancestors[node].length; ++i) {
      ancestors[node][i] =
          (ancestors[node][i - 1] == -1) ? -1 : ancestors[ancestors[node][i - 1]][i - 1];
    }

    if (parent != -1) {
      for (int w = 0; w < weightCounts[node].length; ++w) {
        weightCounts[node][w] = weightCounts[parent][w];
      }
      ++weightCounts[node][weight];
    }

    for (int edge : edgeLists[node]) {
      int adj = (node == u[edge]) ? v[edge] : u[edge];
      if (adj != parent) {
        init(depth + 1, node, weights[edge], adj);
      }
    }
  }

  int findLca(int node1, int node2) {
    if (depths[node1] < depths[node2]) {
      return findLca(node2, node1);
    }

    for (int i = ancestors[node1].length - 1; i >= 0; --i) {
      if (ancestors[node1][i] != -1 && depths[ancestors[node1][i]] >= depths[node2]) {
        node1 = ancestors[node1][i];
      }
    }

    if (node1 == node2) {
      return node1;
    }

    for (int i = ancestors[node1].length - 1; i >= 0; --i) {
      if (ancestors[node1][i] != ancestors[node2][i]) {
        node1 = ancestors[node1][i];
        node2 = ancestors[node2][i];
      }
    }

    return ancestors[node1][0];
  }
}
