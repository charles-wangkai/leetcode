import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
    Map<Integer, Integer> valueToRowIndex = buildValueToIndex(k, rowConditions);
    Map<Integer, Integer> valueToColIndex = buildValueToIndex(k, colConditions);
    if (valueToRowIndex == null || valueToColIndex == null) {
      return new int[0][];
    }

    int[][] result = new int[k][k];
    for (int i = 1; i <= k; ++i) {
      result[valueToRowIndex.get(i)][valueToColIndex.get(i)] = i;
    }

    return result;
  }

  Map<Integer, Integer> buildValueToIndex(int k, int[][] conditions) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[k];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] condition : conditions) {
      adjLists[condition[0] - 1].add(condition[1] - 1);
    }

    List<Integer> sorted = topologicalSort(adjLists);
    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, sorted.size())
            .boxed()
            .collect(Collectors.toMap(i -> sorted.get(i) + 1, i -> i));
    if (Arrays.stream(conditions)
        .anyMatch(condition -> valueToIndex.get(condition[0]) > valueToIndex.get(condition[1]))) {
      return null;
    }

    return valueToIndex;
  }

  List<Integer> topologicalSort(List<Integer>[] adjLists) {
    List<Integer> sorted = new ArrayList<>();
    boolean[] visited = new boolean[adjLists.length];
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        search(sorted, adjLists, visited, i);
      }
    }

    Collections.reverse(sorted);

    return sorted;
  }

  void search(List<Integer> sorted, List<Integer>[] adjLists, boolean[] visited, int node) {
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        search(sorted, adjLists, visited, adj);
      }
    }

    sorted.add(node);
  }
}