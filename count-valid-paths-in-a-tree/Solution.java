import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public long countPaths(int n, int[][] edges) {
    boolean[] centers = new boolean[n];
    for (int i = 0; i < centers.length; ++i) {
      centers[i] = isPrime(i + 1);
    }

    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    int[] sizes = new int[n];
    Arrays.fill(sizes, 1);

    for (int[] edge : edges) {
      if (!centers[edge[0] - 1] && !centers[edge[1] - 1]) {
        int root1 = findRoot(parents, edge[0] - 1);
        int root2 = findRoot(parents, edge[1] - 1);
        parents[root2] = root1;
        sizes[root1] += sizes[root2];
      }
    }

    @SuppressWarnings("unchecked")
    List<Integer>[] adjSizeLists = new List[n];
    for (int i = 0; i < adjSizeLists.length; ++i) {
      adjSizeLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      if (centers[edge[0] - 1] && !centers[edge[1] - 1]) {
        adjSizeLists[edge[0] - 1].add(sizes[findRoot(parents, edge[1] - 1)]);
      } else if (!centers[edge[0] - 1] && centers[edge[1] - 1]) {
        adjSizeLists[edge[1] - 1].add(sizes[findRoot(parents, edge[0] - 1)]);
      }
    }

    long result = 0;
    for (int i = 0; i < centers.length; ++i) {
      if (centers[i]) {
        int adjSizeSum = adjSizeLists[i].stream().mapToInt(Integer::intValue).sum();
        long adjSizeSquareSum =
            adjSizeLists[i].stream().mapToLong(size -> (long) size * size).sum();

        result += adjSizeSum + ((long) adjSizeSum * adjSizeSum - adjSizeSquareSum) / 2;
      }
    }

    return result;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }

  boolean isPrime(int x) {
    if (x == 1) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}
