import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] minEdgeReversals(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    int[] reversalNums = new int[n];
    reversalNums[0] = search1(edges, edgeLists, -1, 0);

    search2(reversalNums, edges, edgeLists, -1, 0);

    return reversalNums;
  }

  void search2(int[] reversalNums, int[][] edges, List<Integer>[] edgeLists, int parent, int node) {
    for (int e : edgeLists[node]) {
      int other = (edges[e][0] == node) ? edges[e][1] : edges[e][0];
      if (other != parent) {
        reversalNums[other] = reversalNums[node] + ((edges[e][0] == other) ? -1 : 1);

        search2(reversalNums, edges, edgeLists, node, other);
      }
    }
  }

  int search1(int[][] edges, List<Integer>[] edgeLists, int parent, int node) {
    int result = 0;
    for (int e : edgeLists[node]) {
      int other = (edges[e][0] == node) ? edges[e][1] : edges[e][0];
      if (other != parent) {
        if (edges[e][0] == other) {
          ++result;
        }

        result += search1(edges, edgeLists, node, other);
      }
    }

    return result;
  }
}
