import java.util.ArrayList;
import java.util.List;

class Solution {
  int goodCount;

  public int countGoodNodes(int[][] edges) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    goodCount = 0;
    search(adjLists, -1, 0);

    return goodCount;
  }

  int search(List<Integer>[] adjLists, int parent, int node) {
    List<Integer> subtreeSizes = new ArrayList<>();
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        subtreeSizes.add(search(adjLists, node, adj));
      }
    }

    if (subtreeSizes.stream().distinct().count() <= 1) {
      ++goodCount;
    }

    return 1 + subtreeSizes.stream().mapToInt(Integer::intValue).sum();
  }
}