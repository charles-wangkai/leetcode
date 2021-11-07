import java.util.ArrayList;
import java.util.List;

class Solution {
  int maxQuality;

  public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeIndices = new List[values.length];
    for (int i = 0; i < edgeIndices.length; ++i) {
      edgeIndices[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeIndices[edges[i][0]].add(i);
      edgeIndices[edges[i][1]].add(i);
    }

    maxQuality = 0;
    search(edgeIndices, values, edges, maxTime, 0, 0, new ArrayList<>(), 0);

    return maxQuality;
  }

  void search(
      List<Integer>[] edgeIndices,
      int[] values,
      int[][] edges,
      int maxTime,
      int time,
      int quality,
      List<Integer> path,
      int node) {
    if (!path.contains(node)) {
      quality += values[node];
    }
    if (node == 0) {
      maxQuality = Math.max(maxQuality, quality);
    }

    path.add(node);

    for (int edgeIndex : edgeIndices[node]) {
      int other = (edges[edgeIndex][0] == node) ? edges[edgeIndex][1] : edges[edgeIndex][0];
      if (time + edges[edgeIndex][2] <= maxTime) {
        search(
            edgeIndices, values, edges, maxTime, time + edges[edgeIndex][2], quality, path, other);
      }
    }

    path.remove(path.size() - 1);
  }
}
