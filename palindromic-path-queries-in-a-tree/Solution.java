import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  int time;

  public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    FenwickTree[] fenwickTrees = new FenwickTree[26];
    for (int i = 0; i < fenwickTrees.length; ++i) {
      fenwickTrees[i] = new FenwickTree(2 * n);
    }

    int[] heights = new int[n];
    int[][] parents = new int[n][Integer.toBinaryString(n).length()];
    int[] inTimes = new int[n];
    int[] outTimes = new int[n];
    time = 1;
    search(heights, parents, inTimes, outTimes, fenwickTrees, s, adjLists, 0, -1, 0);

    for (int i = 1; i < parents[0].length; ++i) {
      for (int node = 0; node < n; ++node) {
        parents[node][i] = (parents[node][i - 1] == -1) ? -1 : parents[parents[node][i - 1]][i - 1];
      }
    }

    List<Boolean> result = new ArrayList<>();
    char[] letters = s.toCharArray();
    for (String query : queries) {
      String[] fields = query.split(" ");
      if (fields[0].equals("update")) {
        int u = Integer.parseInt(fields[1]);
        char c = fields[2].charAt(0);

        fenwickTrees[letters[u] - 'a'].add(inTimes[u], -1);
        fenwickTrees[letters[u] - 'a'].add(outTimes[u], 1);

        letters[u] = c;

        fenwickTrees[letters[u] - 'a'].add(inTimes[u], 1);
        fenwickTrees[letters[u] - 'a'].add(outTimes[u], -1);
      } else {
        int u = Integer.parseInt(fields[1]);
        int v = Integer.parseInt(fields[2]);

        int lca = findLca(heights, parents, u, v);
        int[] counts =
            IntStream.range(0, 26)
                .map(
                    i ->
                        fenwickTrees[i].computePrefixSum(inTimes[u])
                            + fenwickTrees[i].computePrefixSum(inTimes[v])
                            - fenwickTrees[i].computePrefixSum(inTimes[lca]) * 2
                            + ((letters[lca] - 'a' == i) ? 1 : 0))
                .toArray();

        result.add(Arrays.stream(counts).filter(count -> count % 2 == 1).count() <= 1);
      }
    }

    return result;
  }

  void search(
      int[] heights,
      int[][] parents,
      int[] inTimes,
      int[] outTimes,
      FenwickTree[] fenwickTrees,
      String s,
      List<Integer>[] adjLists,
      int height,
      int parent,
      int node) {
    heights[node] = height;
    parents[node][0] = parent;

    inTimes[node] = time;
    ++time;

    fenwickTrees[s.charAt(node) - 'a'].add(inTimes[node], 1);

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search(
            heights, parents, inTimes, outTimes, fenwickTrees, s, adjLists, height + 1, node, adj);
      }
    }

    outTimes[node] = time;
    ++time;

    fenwickTrees[s.charAt(node) - 'a'].add(outTimes[node], -1);
  }

  int findLca(int[] heights, int[][] parents, int node1, int node2) {
    if (heights[node1] < heights[node2]) {
      return findLca(heights, parents, node2, node1);
    }

    int diff = heights[node1] - heights[node2];
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
