import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int countCompleteComponents(int n, int[][] edges) {
    int[] degreeSums = new int[n];
    for (int[] edge : edges) {
      ++degreeSums[edge[0]];
      ++degreeSums[edge[1]];
    }

    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    int[] sizes = new int[n];
    Arrays.fill(sizes, 1);

    for (int[] edge : edges) {
      int root1 = findRoot(parents, edge[0]);
      int root2 = findRoot(parents, edge[1]);
      if (root1 != root2) {
        degreeSums[root1] += degreeSums[root2];
        sizes[root1] += sizes[root2];
        parents[root2] = root1;
      }
    }

    return (int)
        IntStream.range(0, n)
            .filter(i -> parents[i] == -1 && sizes[i] * (sizes[i] - 1) == degreeSums[i])
            .count();
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}
