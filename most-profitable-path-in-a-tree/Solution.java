import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] parents = new int[n];
    buildParents(adjLists, parents, -1, 0);

    int[] bobTimes = new int[n];
    Arrays.fill(bobTimes, Integer.MAX_VALUE);
    for (int node = bob, bobTime = 0; node != -1; node = parents[node], ++bobTime) {
      bobTimes[node] = bobTime;
    }

    return search(amount, adjLists, bobTimes, 0, -1, 0);
  }

  int search(
      int[] amount, List<Integer>[] adjLists, int[] bobTimes, int time, int parent, int node) {
    int current;
    if (time < bobTimes[node]) {
      current = amount[node];
    } else if (time == bobTimes[node]) {
      current = amount[node] / 2;
    } else {
      current = 0;
    }

    int maxSubResult = Integer.MIN_VALUE;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        maxSubResult =
            Math.max(maxSubResult, search(amount, adjLists, bobTimes, time + 1, node, adj));
      }
    }

    return current + ((maxSubResult == Integer.MIN_VALUE) ? 0 : maxSubResult);
  }

  void buildParents(List<Integer>[] adjLists, int[] parents, int parent, int node) {
    parents[node] = parent;

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        buildParents(adjLists, parents, node, adj);
      }
    }
  }
}
