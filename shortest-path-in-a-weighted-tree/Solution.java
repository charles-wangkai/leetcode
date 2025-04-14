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

    int[] binaryIndexedTree = new int[Integer.highestOneBit(2 * n) * 2 + 1];
    int[] values = new int[n];
    for (int[] edge : edges) {
      int node = (inTimes[edge[0] - 1] < inTimes[edge[1] - 1]) ? (edge[1] - 1) : (edge[0] - 1);

      values[node] = edge[2];
      update(binaryIndexedTree, inTimes[node], edge[2]);
      update(binaryIndexedTree, outTimes[node], -edge[2]);
    }

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        int u = query[1];
        int v = query[2];
        int w = query[3];

        int node = (inTimes[u - 1] < inTimes[v - 1]) ? (v - 1) : (u - 1);

        int delta = w - values[node];
        update(binaryIndexedTree, inTimes[node], delta);
        update(binaryIndexedTree, outTimes[node], -delta);
        values[node] = w;
      } else {
        int x = query[1];

        result.add(query(binaryIndexedTree, inTimes[x - 1]));
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  int query(int[] binaryIndexedTree, int index) {
    int result = 0;
    while (index != 0) {
      result += binaryIndexedTree[index];
      index -= index & -index;
    }

    return result;
  }

  void update(int[] binaryIndexedTree, int index, int delta) {
    while (index < binaryIndexedTree.length) {
      binaryIndexedTree[index] += delta;
      index += index & -index;
    }
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
