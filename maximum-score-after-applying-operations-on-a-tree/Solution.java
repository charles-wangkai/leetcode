import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public long maximumScoreAfterOperations(int[][] edges, int[] values) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[values.length];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return Arrays.stream(values).asLongStream().sum() - search(adjLists, values, -1, 0);
  }

  long search(List<Integer>[] adjLists, int[] values, int parent, int node) {
    long childrenSum = 0;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        childrenSum += search(adjLists, values, node, adj);
      }
    }

    return Math.min(values[node], (childrenSum == 0) ? Integer.MAX_VALUE : childrenSum);
  }
}
