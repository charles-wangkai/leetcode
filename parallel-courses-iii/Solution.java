import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
  public int minimumTime(int n, int[][] relations, int[] time) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] relation : relations) {
      adjLists[relation[0] - 1].add(relation[1] - 1);
    }

    List<Integer> sortedNodes = new ArrayList<>();
    boolean[] visited = new boolean[n];
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        search(adjLists, sortedNodes, visited, i);
      }
    }
    Collections.reverse(sortedNodes);

    int[] dp = new int[n];
    for (int node : sortedNodes) {
      dp[node] += time[node];
      for (int adj : adjLists[node]) {
        dp[adj] = Math.max(dp[adj], dp[node]);
      }
    }

    return Arrays.stream(dp).max().getAsInt();
  }

  void search(List<Integer>[] adjLists, List<Integer> sortedNodes, boolean[] visited, int node) {
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        search(adjLists, sortedNodes, visited, adj);
      }
    }

    sortedNodes.add(node);
  }
}
