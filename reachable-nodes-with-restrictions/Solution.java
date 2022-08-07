import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int reachableNodes(int n, int[][] edges, int[] restricted) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return search(
        adjLists, Arrays.stream(restricted).boxed().collect(Collectors.toSet()), new boolean[n], 0);
  }

  int search(List<Integer>[] adjLists, Set<Integer> restrictedSet, boolean[] visited, int node) {
    visited[node] = true;

    int result = 1;
    for (int adj : adjLists[node]) {
      if (!restrictedSet.contains(adj) && !visited[adj]) {
        result += search(adjLists, restrictedSet, visited, adj);
      }
    }

    return result;
  }
}