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
    search(seats, adjLists, 0, -1);

    return cost;
  }

  int search(int seats, List<Integer>[] adjLists, int node, int parent) {
    int result = 1;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result += search(seats, adjLists, adj, node);
      }
    }
    if (parent != -1) {
      cost += (result + seats - 1) / seats;
    }

    return result;
  }
}
