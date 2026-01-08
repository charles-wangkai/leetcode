import java.util.ArrayList;
import java.util.List;

class Solution {
  int time;

  public int[] treeQueries(int n, int[][] edges, int[][] queries) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0] - 1].add(i);
      edgeLists[edges[i][1] - 1].add(i);
    }

    int[] inTimes = new int[n];
    int[] outTimes = new int[n];
    time = 1;
    search(inTimes, outTimes, edges, edgeLists, -1, 0);

    FenwickTree fenwickTree = new FenwickTree(2 * n);
    int[] values = new int[n];
    for (int[] edge : edges) {
      int node = (inTimes[edge[0] - 1] < inTimes[edge[1] - 1]) ? (edge[1] - 1) : (edge[0] - 1);

      values[node] = edge[2];
      fenwickTree.add(inTimes[node], edge[2]);
      fenwickTree.add(outTimes[node], -edge[2]);
    }

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        int u = query[1];
        int v = query[2];
        int w = query[3];

        int node = (inTimes[u - 1] < inTimes[v - 1]) ? (v - 1) : (u - 1);

        int delta = w - values[node];
        fenwickTree.add(inTimes[node], delta);
        fenwickTree.add(outTimes[node], -delta);
        values[node] = w;
      } else {
        int x = query[1];

        result.add(fenwickTree.computePrefixSum(inTimes[x - 1]));
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  void search(
      int[] inTimes,
      int[] outTimes,
      int[][] edges,
      List<Integer>[] edgeLists,
      int parent,
      int node) {
    inTimes[node] = time;
    ++time;

    for (int e : edgeLists[node]) {
      int other = (edges[e][0] - 1 == node) ? (edges[e][1] - 1) : (edges[e][0] - 1);
      if (other != parent) {
        search(inTimes, outTimes, edges, edgeLists, node, other);
      }
    }

    outTimes[node] = time;
    ++time;
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
