import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int[] findMedian(int n, int[][] edges, int[][] queries) {
    Tree tree =
        new Tree(
            Arrays.stream(edges).mapToInt(edge -> edge[0]).toArray(),
            Arrays.stream(edges).mapToInt(edge -> edge[1]).toArray(),
            Arrays.stream(edges).mapToInt(edge -> edge[2]).toArray());

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int from = query[0];
              int to = query[1];
              int lca = tree.findLca(from, to);

              long totalDistance = tree.computeDistance(from, to);
              long upDistance = tree.computeDistance(from, lca);

              int result = -1;
              if (upDistance * 2 >= totalDistance) {
                int lower = tree.depths[lca];
                int upper = tree.depths[from];
                while (lower <= upper) {
                  int middle = (lower + upper) / 2;
                  int node = tree.findAncestor(from, middle);
                  if (tree.computeDistance(from, node) * 2 >= totalDistance) {
                    result = node;
                    lower = middle + 1;
                  } else {
                    upper = middle - 1;
                  }
                }
              } else {
                int lower = tree.depths[lca];
                int upper = tree.depths[to];
                while (lower <= upper) {
                  int middle = (lower + upper) / 2;
                  int node = tree.findAncestor(to, middle);
                  if ((upDistance + tree.computeDistance(lca, node)) * 2 >= totalDistance) {
                    result = node;
                    upper = middle - 1;
                  } else {
                    lower = middle + 1;
                  }
                }
              }

              return result;
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
  long[] pathWeights;
  int[][] ancestors;

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
    pathWeights = new long[n];
    ancestors = new int[n][Integer.toBinaryString(n).length()];
    init(0, 0, -1, 0);
  }

  private void init(int depth, long pathWeight, int parent, int node) {
    depths[node] = depth;
    pathWeights[node] = pathWeight;

    ancestors[node][0] = parent;
    for (int i = 1; i < ancestors[node].length; ++i) {
      ancestors[node][i] =
          (ancestors[node][i - 1] == -1) ? -1 : ancestors[ancestors[node][i - 1]][i - 1];
    }

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

  int findAncestor(int node, int targetDepth) {
    for (int i = ancestors[node].length - 1; i >= 0; --i) {
      if (ancestors[node][i] != -1 && depths[ancestors[node][i]] >= targetDepth) {
        node = ancestors[node][i];
      }
    }

    return node;
  }

  long computeDistance(int node1, int node2) {
    return pathWeights[node1] + pathWeights[node2] - 2 * pathWeights[findLca(node1, node2)];
  }
}
