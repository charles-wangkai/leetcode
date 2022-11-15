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
    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[n];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    int[] parents = new int[n];
    Arrays.fill(parents, Integer.MAX_VALUE);
    buildTree(adjLists, childLists, parents, -1, 0);

    int[] bobTimes = new int[n];
    Arrays.fill(bobTimes, Integer.MAX_VALUE);
    for (int node = bob, bobTime = 0; node != -1; node = parents[node], ++bobTime) {
      bobTimes[node] = bobTime;
    }

    return search(amount, childLists, bobTimes, 0, 0, 0);
  }

  int search(
      int[] amount, List<Integer>[] childLists, int[] bobTimes, int time, int income, int node) {
    if (bobTimes[node] > time) {
      income += amount[node];
    } else if (bobTimes[node] == time) {
      income += amount[node] / 2;
    }

    if (childLists[node].isEmpty()) {
      return income;
    }

    int result = Integer.MIN_VALUE;
    for (int child : childLists[node]) {
      result = Math.max(result, search(amount, childLists, bobTimes, time + 1, income, child));
    }

    return result;
  }

  void buildTree(
      List<Integer>[] adjLists, List<Integer>[] childLists, int[] parents, int parent, int node) {
    if (parent != -1) {
      childLists[parent].add(node);
    }
    parents[node] = parent;

    for (int adj : adjLists[node]) {
      if (parents[adj] == Integer.MAX_VALUE) {
        buildTree(adjLists, childLists, parents, node, adj);
      }
    }
  }
}
