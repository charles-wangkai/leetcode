import java.util.HashSet;
import java.util.Set;

class Solution {
  public int minTrioDegree(int n, int[][] edges) {
    int[] degrees = new int[n];
    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[n];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }

    for (int[] edge : edges) {
      ++degrees[edge[0] - 1];
      ++degrees[edge[1] - 1];

      adjSets[edge[0] - 1].add(edge[1] - 1);
      adjSets[edge[1] - 1].add(edge[0] - 1);
    }

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        for (int k = j + 1; k < n; ++k) {
          if (adjSets[i].contains(j) && adjSets[j].contains(k) && adjSets[k].contains(i)) {
            result = Math.min(result, degrees[i] + degrees[j] + degrees[k] - 6);
          }
        }
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}
