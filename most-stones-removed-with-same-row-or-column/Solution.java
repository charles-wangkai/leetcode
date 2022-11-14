import java.util.ArrayList;
import java.util.List;

class Solution {
  public int removeStones(int[][] stones) {
    int n = stones.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
          adjLists[i].add(j);
          adjLists[j].add(i);
        }
      }
    }

    int result = 0;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; ++i) {
      if (visited[i]) {
        ++result;
      } else {
        search(stones, adjLists, visited, i);
      }
    }

    return result;
  }

  void search(int[][] stones, List<Integer>[] adjLists, boolean[] visited, int index) {
    visited[index] = true;

    for (int adj : adjLists[index]) {
      if (!visited[adj]) {
        search(stones, adjLists, visited, adj);
      }
    }
  }
}
