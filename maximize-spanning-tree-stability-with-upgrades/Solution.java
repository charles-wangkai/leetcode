import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxStability(int n, int[][] edges, int k) {
    Pair[] mandatoryPairs =
        Arrays.stream(edges)
            .filter(edge -> edge[3] == 1)
            .map(edge -> new Pair(edge[0], edge[1]))
            .toArray(Pair[]::new);
    if (computeComponentNum(n, mandatoryPairs) != n - mandatoryPairs.length) {
      return -1;
    }

    int result = -1;
    int lower = 0;
    int upper =
        Arrays.stream(edges)
            .filter(edge -> edge[3] == 1)
            .mapToInt(edge -> edge[2])
            .min()
            .orElse(Arrays.stream(edges).mapToInt(edge -> edge[2] * 2).max().getAsInt());
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(n, edges, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int n, int[][] edges, int k, int strengthLimit) {
    if (computeComponentNum(
            n,
            Arrays.stream(edges)
                .filter(edge -> edge[3] == 1 || edge[2] * 2 >= strengthLimit)
                .map(edge -> new Pair(edge[0], edge[1]))
                .toArray(Pair[]::new))
        != 1) {
      return false;
    }

    return computeComponentNum(
                n,
                Arrays.stream(edges)
                    .filter(edge -> edge[3] == 1 || edge[2] >= strengthLimit)
                    .map(edge -> new Pair(edge[0], edge[1]))
                    .toArray(Pair[]::new))
            - 1
        <= k;
  }

  int computeComponentNum(int n, Pair[] pairs) {
    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    for (Pair pair : pairs) {
      int root1 = findRoot(parents, pair.node1());
      int root2 = findRoot(parents, pair.node2());
      if (root1 != root2) {
        parents[root2] = root1;
      }
    }

    return (int) IntStream.range(0, n).map(i -> findRoot(parents, i)).distinct().count();
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}

record Pair(int node1, int node2) {}
