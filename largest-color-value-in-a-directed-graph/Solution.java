import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int MAX_COLOR_NUM = 26;

  public int largestPathValue(String colors, int[][] edges) {
    int n = colors.length();

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
    }

    List<Integer> sorted = topologicalSort(adjLists);
    if (hasCycle(edges, sorted)) {
      return -1;
    }

    int[][] maxCounts = new int[n][MAX_COLOR_NUM];
    for (int i = sorted.size() - 1; i >= 0; --i) {
      int node = sorted.get(i);
      for (int j = 0; j < MAX_COLOR_NUM; ++j) {
        int j_ = j;
        maxCounts[node][j] =
            ((colors.charAt(node) - 'a' == j) ? 1 : 0)
                + adjLists[node].stream().mapToInt(adj -> maxCounts[adj][j_]).max().orElse(0);
      }
    }

    return Arrays.stream(maxCounts)
        .mapToInt(x -> Arrays.stream(x).max().getAsInt())
        .max()
        .getAsInt();
  }

  List<Integer> topologicalSort(List<Integer>[] adjLists) {
    int n = adjLists.length;

    List<Integer> sorted = new ArrayList<>();
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; ++i) {
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

  boolean hasCycle(int[][] edges, List<Integer> sorted) {
    Map<Integer, Integer> nodeToIndex =
        IntStream.range(0, sorted.size()).boxed().collect(Collectors.toMap(sorted::get, i -> i));

    return Arrays.stream(edges)
        .anyMatch(edge -> nodeToIndex.get(edge[0]) >= nodeToIndex.get(edge[1]));
  }
}
