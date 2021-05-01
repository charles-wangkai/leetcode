import java.util.ArrayList;
import java.util.List;

class Solution {
  public int countComponents(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    boolean[] visited = new boolean[n];
    int componentNum = 0;
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        search(adjLists, visited, i);
        ++componentNum;
      }
    }

    return componentNum;
  }

  void search(List<Integer>[] adjLists, boolean[] visited, int node) {
    visited[node] = true;
    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        search(adjLists, visited, adj);
      }
    }
  }
}
