import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public List<List<Integer>> getAncestors(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
    }

    List<Integer> sorted = topologicalSort(adjLists);

    List<Set<Integer>> ancestorSets = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      ancestorSets.add(new HashSet<>());
    }
    for (int node : sorted) {
      for (int adj : adjLists[node]) {
        ancestorSets.get(adj).add(node);
        ancestorSets.get(adj).addAll(ancestorSets.get(node));
      }
    }

    return ancestorSets.stream()
        .map(ancestorSet -> ancestorSet.stream().sorted().collect(Collectors.toList()))
        .collect(Collectors.toList());
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
}