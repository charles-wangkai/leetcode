// https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths-ii/solutions/6754998/c-very-easy-to-understand-beginner-friendly-clean-intuitive-solution/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int[] minimumWeight(int[][] edges, int[][] queries) {
    Tree tree =
        new Tree(
            Arrays.stream(edges).mapToInt(edge -> edge[0]).toArray(),
            Arrays.stream(edges).mapToInt(edge -> edge[1]).toArray(),
            Arrays.stream(edges).mapToInt(edge -> edge[2]).toArray());

    return Arrays.stream(queries)
        .mapToInt(
            query ->
                (tree.computeDistance(query[0], query[1])
                        + tree.computeDistance(query[1], query[2])
                        + tree.computeDistance(query[2], query[0]))
                    / 2)
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
  int[] pathWeights;

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
    pathWeights = new int[n];
    init(0, 0, -1, 0);
  }

  private void init(int depth, int pathWeight, int parent, int node) {
    depths[node] = depth;

    ancestors[node][0] = parent;
    for (int i = 1; i < ancestors[node].length; ++i) {
      ancestors[node][i] =
          (ancestors[node][i - 1] == -1) ? -1 : ancestors[ancestors[node][i - 1]][i - 1];
    }

    pathWeights[node] = pathWeight;

    for (int edge : edgeLists[node]) {
      int adj = (node == u[edge]) ? v[edge] : u[edge];
      if (adj != parent) {
        init(depth + 1, pathWeight + weights[edge], node, adj);
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

  int computeDistance(int node1, int node2) {
    return pathWeights[node1] + pathWeights[node2] - 2 * pathWeights[findLca(node1, node2)];
  }
}
