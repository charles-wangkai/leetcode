import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int minScore(int n, int[][] roads) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] road : roads) {
      adjLists[road[0] - 1].add(road[1] - 1);
      adjLists[road[1] - 1].add(road[0] - 1);
    }

    boolean[] visited = new boolean[n];
    search(adjLists, visited, 0);

    return Arrays.stream(roads)
        .filter(road -> visited[road[0] - 1] && visited[road[1] - 1])
        .mapToInt(road -> road[2])
        .min()
        .getAsInt();
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
