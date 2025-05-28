import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
    int n = edges.length + 1;

    int[] powers = new int[n];
    powers[0] = 1;
    for (int i = 1; i < powers.length; ++i) {
      powers[i] = multiplyMod(powers[i - 1], 2);
    }

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0] - 1].add(edge[1] - 1);
      adjLists[edge[1] - 1].add(edge[0] - 1);
    }

    int[] depths = new int[n];
    int[][] parents = new int[n][Integer.toBinaryString(n).length()];
    search(depths, parents, adjLists, 0, -1, 0);

    for (int i = 1; i < parents[0].length; ++i) {
      for (int node = 0; node < n; ++node) {
        parents[node][i] = (parents[node][i - 1] == -1) ? -1 : parents[parents[node][i - 1]][i - 1];
      }
    }

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int distance =
                  depths[query[0] - 1]
                      + depths[query[1] - 1]
                      - 2 * depths[findLca(depths, parents, query[0] - 1, query[1] - 1)];

              return (distance == 0) ? 0 : powers[distance - 1];
            })
        .toArray();
  }

  int findLca(int[] depths, int[][] parents, int node1, int node2) {
    if (depths[node1] < depths[node2]) {
      return findLca(depths, parents, node2, node1);
    }

    int diff = depths[node1] - depths[node2];
    for (int i = 0; diff != 0; ++i) {
      if (((diff >> i) & 1) == 1) {
        node1 = parents[node1][i];

        diff -= 1 << i;
      }
    }
    if (node1 == node2) {
      return node1;
    }

    while (true) {
      int index = -1;
      while (parents[node1][index + 1] != parents[node2][index + 1]) {
        ++index;
      }
      if (index == -1) {
        break;
      }

      node1 = parents[node1][index];
      node2 = parents[node2][index];
    }

    return parents[node1][0];
  }

  void search(
      int[] depths, int[][] parents, List<Integer>[] adjLists, int depth, int parent, int node) {
    depths[node] = depth;
    parents[node][0] = parent;

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search(depths, parents, adjLists, depth + 1, node, adj);
      }
    }
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}