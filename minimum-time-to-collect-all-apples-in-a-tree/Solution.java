import java.util.ArrayList;
import java.util.List;

class Solution {
  public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return search(adjLists, hasApple, new boolean[n], 0).time();
  }

  Outcome search(List<Integer>[] adjLists, List<Boolean> hasApple, boolean[] visited, int v) {
    visited[v] = true;

    int time = 0;
    boolean foundApple = hasApple.get(v);
    for (int adj : adjLists[v]) {
      if (!visited[adj]) {
        Outcome subResult = search(adjLists, hasApple, visited, adj);
        if (subResult.foundApple()) {
          time += 2 + subResult.time();
          foundApple = true;
        }
      }
    }

    return new Outcome(time, foundApple);
  }
}

record Outcome(int time, boolean foundApple) {}
