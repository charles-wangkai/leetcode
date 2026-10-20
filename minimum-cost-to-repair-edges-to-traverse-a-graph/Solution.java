import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
  public int minCost(int n, int[][] edges, int k) {
    int result = -1;
    int lower = 0;
    int upper = Arrays.stream(edges).mapToInt(edge -> edge[2]).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(n, edges, k, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int n, int[][] edges, int k, int limit) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      if (edge[2] <= limit) {
        adjLists[edge[0]].add(edge[1]);
        adjLists[edge[1]].add(edge[0]);
      }
    }

    int[] distances = new int[n];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[0] = 0;

    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);

    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int adj : adjLists[head]) {
        if (distances[adj] == Integer.MAX_VALUE) {
          distances[adj] = distances[head] + 1;
          queue.offer(adj);
        }
      }
    }

    return distances[n - 1] <= k;
  }
}