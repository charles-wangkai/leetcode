import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int minTime(int n, int[][] edges, int k) {
    Map<Integer, List<Integer>> timeToEdgeIndices = new HashMap<>();
    for (int i = 0; i < edges.length; ++i) {
      timeToEdgeIndices.putIfAbsent(edges[i][2], new ArrayList<>());
      timeToEdgeIndices.get(edges[i][2]).add(i);
    }

    int[] sortedTimes =
        timeToEdgeIndices.keySet().stream()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    int result = -1;
    int componentNum = n;
    int[] parents = new int[n];
    Arrays.fill(parents, -1);
    for (int i = 0; i <= sortedTimes.length; ++i) {
      if (componentNum >= k) {
        result = (i == sortedTimes.length) ? 0 : sortedTimes[i];
      }

      if (i != sortedTimes.length) {
        for (int edgeIndex : timeToEdgeIndices.get(sortedTimes[i])) {
          int root1 = findRoot(parents, edges[edgeIndex][0]);
          int root2 = findRoot(parents, edges[edgeIndex][1]);
          if (root1 != root2) {
            parents[root2] = root1;
            --componentNum;
          }
        }
      }
    }

    return result;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}