import java.util.ArrayList;
import java.util.List;

class Solution {
  public boolean validPath(int n, int[][] edges, int source, int destination) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return search(adjLists, destination, new boolean[n], source);
  }

  boolean search(List<Integer>[] adjLists, int destination, boolean[] visited, int node) {
    visited[node] = true;
    if (node == destination) {
      return true;
    }

    for (int adj : adjLists[node]) {
      if (!visited[adj] && search(adjLists, destination, visited, adj)) {
        return true;
      }
    }

    return false;
  }
}
