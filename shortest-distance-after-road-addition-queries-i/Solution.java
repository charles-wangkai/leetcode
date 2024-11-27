import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
  public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < n - 1; ++i) {
      adjLists[i].add(i + 1);
    }

    int[] result = new int[queries.length];
    for (int i = 0; i < result.length; ++i) {
      adjLists[queries[i][0]].add(queries[i][1]);
      result[i] = computeMinDistance(adjLists);
    }

    return result;
  }

  int computeMinDistance(List<Integer>[] adjLists) {
    int[] distances = new int[adjLists.length];
    Arrays.fill(distances, -1);
    distances[0] = 0;

    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);

    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int adj : adjLists[head]) {
        if (distances[adj] == -1) {
          distances[adj] = distances[head] + 1;
          queue.offer(adj);
        }
      }
    }

    return distances[distances.length - 1];
  }
}