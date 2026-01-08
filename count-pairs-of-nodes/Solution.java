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

    FenwickTree fenwickTree = new FenwickTree(edges.length);
    for (int i = 0; i < degrees.length; ++i) {
      fenwickTree.add(degrees[i] + 1, 1);
    }

    int[] result = new int[queries.length];
    for (int from = 0; from < n; ++from) {
      fenwickTree.add(degrees[from] + 1, -1);

      for (int to : toMaps[from].keySet()) {
        fenwickTree.add(degrees[to] + 1, -1);
        fenwickTree.add(degrees[to] - toMaps[from].get(to) + 1, 1);
      }

      for (int i = 0; i < result.length; ++i) {
        result[i] +=
            n
                - 1
                - from
                - fenwickTree.computePrefixSum(Math.max(0, queries[i] - degrees[from] + 1));
      }

      for (int to : toMaps[from].keySet()) {
        fenwickTree.add(degrees[to] - toMaps[from].get(to) + 1, -1);
        fenwickTree.add(degrees[to] + 1, 1);
      }
    }

    return result;
  }
}

class FenwickTree {
  int[] a;

  FenwickTree(int size) {
    a = new int[Integer.highestOneBit(size) * 2 + 1];
  }

  void add(int pos, int delta) {
    while (pos < a.length) {
      a[pos] += delta;
      pos += pos & -pos;
    }
  }

  int computePrefixSum(int pos) {
    int result = 0;
    while (pos != 0) {
      result += a[pos];
      pos -= pos & -pos;
    }

    return result;
  }
}
