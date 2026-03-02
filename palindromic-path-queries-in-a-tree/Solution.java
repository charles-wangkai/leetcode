import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
    Tree tree =
        new Tree(
            Arrays.stream(edges).mapToInt(edge -> edge[0]).toArray(),
            Arrays.stream(edges).mapToInt(edge -> edge[1]).toArray(),
            s);

    List<Boolean> result = new ArrayList<>();
    char[] letters = s.toCharArray();
    for (String query : queries) {
      String[] fields = query.split(" ");
      if (fields[0].equals("update")) {
        int u = Integer.parseInt(fields[1]);
        char c = fields[2].charAt(0);

        tree.fenwickTrees[letters[u] - 'a'].add(tree.inTimes[u], -1);
        tree.fenwickTrees[letters[u] - 'a'].add(tree.outTimes[u], 1);

        letters[u] = c;

        tree.fenwickTrees[letters[u] - 'a'].add(tree.inTimes[u], 1);
        tree.fenwickTrees[letters[u] - 'a'].add(tree.outTimes[u], -1);
      } else {
        int u = Integer.parseInt(fields[1]);
        int v = Integer.parseInt(fields[2]);

        int lca = tree.findLca(u, v);
        int[] counts =
            IntStream.range(0, 26)
                .map(
                    i ->
                        tree.fenwickTrees[i].computePrefixSum(tree.inTimes[u])
                            + tree.fenwickTrees[i].computePrefixSum(tree.inTimes[v])
                            - tree.fenwickTrees[i].computePrefixSum(tree.inTimes[lca]) * 2
                            + ((letters[lca] - 'a' == i) ? 1 : 0))
                .toArray();

        result.add(Arrays.stream(counts).filter(count -> count % 2 == 1).count() <= 1);
      }
    }

    return result;
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

class Tree {
  int n;
  int[] u;
  int[] v;
  String s;
  List<Integer>[] edgeLists;
  int[] depths;
  int[][] ancestors;
  FenwickTree[] fenwickTrees;
  int[] inTimes;
  int[] outTimes;
  int time = 1;

  @SuppressWarnings("unchecked")
  Tree(int[] u, int[] v, String s) {
    n = u.length + 1;

    this.u = u;
    this.v = v;
    this.s = s;

    edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < u.length; ++i) {
      edgeLists[u[i]].add(i);
      edgeLists[v[i]].add(i);
    }

    depths = new int[n];
    ancestors = new int[n][Integer.toBinaryString(n).length()];

    fenwickTrees = new FenwickTree[26];
    for (int i = 0; i < fenwickTrees.length; ++i) {
      fenwickTrees[i] = new FenwickTree(2 * n);
    }

    inTimes = new int[n];
    outTimes = new int[n];

    init(0, -1, 0);
  }

  private void init(int depth, int parent, int node) {
    depths[node] = depth;

    ancestors[node][0] = parent;
    for (int i = 1; i < ancestors[node].length; ++i) {
      ancestors[node][i] =
          (ancestors[node][i - 1] == -1) ? -1 : ancestors[ancestors[node][i - 1]][i - 1];
    }

    inTimes[node] = time;
    ++time;

    fenwickTrees[s.charAt(node) - 'a'].add(inTimes[node], 1);

    for (int edge : edgeLists[node]) {
      int adj = (node == u[edge]) ? v[edge] : u[edge];
      if (adj != parent) {
        init(depth + 1, node, adj);
      }
    }

    outTimes[node] = time;
    ++time;

    fenwickTrees[s.charAt(node) - 'a'].add(outTimes[node], -1);
  }

  int findLca(int node1, int node2) {
    if (depths[node1] < depths[node2]) {
      return findLca(node2, node1);
    }

    for (int i = ancestors[node1].length - 1; i >= 0; --i) {
      if (ancestors[node1][i] != -1 && depths[ancestors[node1][i]] >= depths[node2]) {
        node1 = ancestors[node1][i];
      }
    }

    if (node1 == node2) {
      return node1;
    }

    for (int i = ancestors[node1].length - 1; i >= 0; --i) {
      if (ancestors[node1][i] != ancestors[node2][i]) {
        node1 = ancestors[node1][i];
        node2 = ancestors[node2][i];
      }
    }

    return ancestors[node1][0];
  }
}
