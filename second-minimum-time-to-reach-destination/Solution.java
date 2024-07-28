import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
  public int secondMinimum(int n, int[][] edges, int time, int change) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjList = new List[n];
    for (int i = 0; i < adjList.length; ++i) {
      adjList[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjList[edge[0] - 1].add(edge[1] - 1);
      adjList[edge[1] - 1].add(edge[0] - 1);
    }

    int[] distances = new int[n];
    Arrays.fill(distances, -1);
    distances[0] = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int adj : adjList[head]) {
        if (distances[adj] == -1) {
          distances[adj] = distances[head] + 1;
          queue.offer(adj);
        }
      }
    }

    int edgeNum =
        distances[n - 1] + (canExtendOne(new Boolean[n], adjList, distances, n - 1) ? 1 : 2);

    int result = 0;
    for (int i = 0; i < edgeNum; ++i) {
      if (result / change % 2 == 1) {
        result += change - result % change;
      }
      result += time;
    }

    return result;
  }

  boolean canExtendOne(
      Boolean[] oneExtendables, List<Integer>[] adjList, int[] distances, int node) {
    if (oneExtendables[node] == null) {
      oneExtendables[node] =
          node != 0
              && adjList[node].stream()
                  .anyMatch(
                      adj ->
                          distances[adj] == distances[node]
                              || (distances[adj] == distances[node] - 1
                                  && canExtendOne(oneExtendables, adjList, distances, adj)));
    }

    return oneExtendables[node];
  }
}
