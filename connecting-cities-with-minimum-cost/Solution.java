import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minimumCost(int n, int[][] connections) {
    Arrays.sort(connections, Comparator.comparing(connection -> connection[2]));

    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    int costSum = 0;
    for (int[] connection : connections) {
      int root1 = findRoot(parents, connection[0] - 1);
      int root2 = findRoot(parents, connection[1] - 1);
      if (root1 != root2) {
        parents[root2] = root1;
        costSum += connection[2];
      }
    }

    return (IntStream.range(0, n).map(node -> findRoot(parents, node)).distinct().count() == 1)
        ? costSum
        : -1;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}