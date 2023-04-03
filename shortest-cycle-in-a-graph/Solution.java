import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public int findShortestCycle(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int result = IntStream.range(0, n).map(i -> computeMinCycle(adjLists, i)).min().getAsInt();

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  int computeMinCycle(List<Integer>[] adjLists, int start) {
    int result = Integer.MAX_VALUE;
    int[] distances = new int[adjLists.length];
    Arrays.fill(distances, -1);
    distances[start] = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int adj : adjLists[head]) {
        if (distances[adj] == -1) {
          for (int nextAdj : adjLists[adj]) {
            if (nextAdj != head && distances[nextAdj] != -1) {
              result = Math.min(result, distances[head] + distances[nextAdj] + 2);
            }
          }

          distances[adj] = distances[head] + 1;
          queue.offer(adj);
        }
      }
    }

    return result;
  }
}
