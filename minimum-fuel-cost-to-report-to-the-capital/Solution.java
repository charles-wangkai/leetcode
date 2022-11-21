import java.util.ArrayList;
import java.util.List;

class Solution {
  long cost;

  public long minimumFuelCost(int[][] roads, int seats) {
    int n = roads.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] road : roads) {
      adjLists[road[0]].add(road[1]);
      adjLists[road[1]].add(road[0]);
    }

    cost = 0;
    search(seats, adjLists, new boolean[n], 0, 0);

    return cost;
  }

  int search(int seats, List<Integer>[] adjLists, boolean[] visited, int node, int depth) {
    visited[node] = true;

    int result = 1;
    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        result += search(seats, adjLists, visited, adj, depth + 1);
      }
    }
    if (depth != 0) {
      cost += (result + seats - 1) / seats;
    }

    return result;
  }
}
