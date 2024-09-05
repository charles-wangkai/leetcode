import java.util.ArrayList;
import java.util.List;

class Solution {
  int maxSubtreeSize;

  public int maximumSubtreeSize(int[][] edges, int[] colors) {
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

    maxSubtreeSize = 0;
    search(adjLists, colors, -1, 0);

    return maxSubtreeSize;
  }

  Outcome search(List<Integer>[] adjLists, int[] colors, int parent, int node) {
    Outcome result = new Outcome(colors[node], 1);
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result = merge(result, search(adjLists, colors, node, adj));
      }
    }

    if (result != null) {
      maxSubtreeSize = Math.max(maxSubtreeSize, result.size());
    }

    return result;
  }

  Outcome merge(Outcome o1, Outcome o2) {
    return (o1 == null || o2 == null || o1.color() != o2.color())
        ? null
        : new Outcome(o1.color(), o1.size() + o2.size());
  }
}

record Outcome(int color, int size) {}
