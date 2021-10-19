import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public int networkBecomesIdle(int[][] edges, int[] patience) {
    int n = patience.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] distances = new int[n];
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

    return IntStream.range(1, n)
        .map(i -> (distances[i] * 2 - 1) / patience[i] * patience[i] + distances[i] * 2 + 1)
        .max()
        .getAsInt();
  }
}
