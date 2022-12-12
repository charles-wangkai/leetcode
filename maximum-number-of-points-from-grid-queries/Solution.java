import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int[] maxPoints(int[][] grid, int[] queries) {
    int m = grid.length;
    int n = grid[0].length;

    int[] result = new int[queries.length];
    int[] parents = new int[m * n];
    Arrays.fill(parents, -1);
    int[] sizes = new int[m * n];
    int[] sortedPoints =
        IntStream.range(0, m)
            .flatMap(r -> IntStream.range(0, n).map(c -> r * n + c))
            .boxed()
            .sorted(Comparator.comparing(i -> grid[i / n][i % n]))
            .mapToInt(x -> x)
            .toArray();
    int sortedPointIndex = 0;
    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i]))
            .mapToInt(x -> x)
            .toArray();
    for (int queryIndex : sortedQueryIndices) {
      while (sortedPointIndex != sortedPoints.length
          && grid[sortedPoints[sortedPointIndex] / n][sortedPoints[sortedPointIndex] % n]
              < queries[queryIndex]) {
        int point = sortedPoints[sortedPointIndex];
        parents[point] = point;
        sizes[point] = 1;

        for (int i = 0; i < R_OFFSETS.length; ++i) {
          int adjR = point / n + R_OFFSETS[i];
          int adjC = point % n + C_OFFSETS[i];
          int adj = adjR * n + adjC;
          if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n && parents[adj] != -1) {
            int root1 = findRoot(parents, point);
            int root2 = findRoot(parents, adj);
            if (root1 != root2) {
              parents[root2] = root1;
              sizes[root1] += sizes[root2];
            }
          }
        }

        ++sortedPointIndex;
      }

      if (parents[0] != -1) {
        result[queryIndex] = sizes[findRoot(parents, 0)];
      }
    }

    return result;
  }

  int findRoot(int[] parents, int node) {
    int root = node;
    while (parents[root] != root) {
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
