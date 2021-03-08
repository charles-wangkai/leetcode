import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] countPairs(int n, int[][] edges, int[] queries) {
    int[] degrees = new int[n];
    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] toMaps = new Map[n];
    for (int i = 0; i < toMaps.length; ++i) {
      toMaps[i] = new HashMap<>();
    }
    for (int[] edge : edges) {
      int u = Math.min(edge[0], edge[1]) - 1;
      int v = Math.max(edge[0], edge[1]) - 1;

      ++degrees[u];
      ++degrees[v];

      toMaps[u].put(v, toMaps[u].getOrDefault(v, 0) + 1);
    }

    int[] A = new int[edges.length];
    for (int i = 0; i < degrees.length; ++i) {
      add(A, degrees[i], 1);
    }

    int[] result = new int[queries.length];
    for (int from = 0; from < n; ++from) {
      add(A, degrees[from], -1);

      for (int to : toMaps[from].keySet()) {
        add(A, degrees[to], -1);
        add(A, degrees[to] - toMaps[from].get(to), 1);
      }

      for (int i = 0; i < result.length; ++i) {
        result[i] += n - 1 - from - prefixSum(A, queries[i] - degrees[from] + 1);
      }

      for (int to : toMaps[from].keySet()) {
        add(A, degrees[to] - toMaps[from].get(to), -1);
        add(A, degrees[to], 1);
      }
    }

    return result;
  }

  int LSBIT(int i) {
    return i & -i;
  }

  int prefixSum(int[] A, int i) {
    int sum = 0;
    while (i > 0) {
      sum += A[i - 1];
      i -= LSBIT(i);
    }

    return sum;
  }

  void add(int[] A, int i, int delta) {
    while (i < A.length) {
      A[i] += delta;
      i += LSBIT(i + 1);
    }
  }
}
