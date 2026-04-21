import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[source.length];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] allowedSwap : allowedSwaps) {
      adjLists[allowedSwap[0]].add(allowedSwap[1]);
      adjLists[allowedSwap[1]].add(allowedSwap[0]);
    }

    boolean[] visited = new boolean[adjLists.length];
    int result = 0;
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        List<Integer> group = new ArrayList<>();
        search(group, adjLists, visited, i);

        Map<Integer, Integer> sourceValueToCount = buildValueToCount(group, source);
        Map<Integer, Integer> targetValueToCount = buildValueToCount(group, target);
        result +=
            group.size()
                - sourceValueToCount.keySet().stream()
                    .mapToInt(
                        value ->
                            Math.min(
                                sourceValueToCount.get(value),
                                targetValueToCount.getOrDefault(value, 0)))
                    .sum();
      }
    }

    return result;
  }

  void search(List<Integer> group, List<Integer>[] adjLists, boolean[] visited, int node) {
    visited[node] = true;
    group.add(node);

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        search(group, adjLists, visited, adj);
      }
    }
  }

  Map<Integer, Integer> buildValueToCount(List<Integer> group, int[] values) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int index : group) {
      valueToCount.put(values[index], valueToCount.getOrDefault(values[index], 0) + 1);
    }

    return valueToCount;
  }
}
