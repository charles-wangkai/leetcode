import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
  public List<Integer> eventualSafeNodes(int[][] graph) {
    int n = graph.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] fromLists = new List[n];
    for (int i = 0; i < fromLists.length; ++i) {
      fromLists[i] = new ArrayList<>();
    }
    for (int from = 0; from < graph.length; ++from) {
      for (int to : graph[from]) {
        fromLists[to].add(from);
      }
    }

    List<Integer> safes = new ArrayList<>();
    Queue<Integer> queue = new ArrayDeque<>();
    int[] rests = Arrays.stream(graph).mapToInt(x -> x.length).toArray();
    for (int i = 0; i < rests.length; ++i) {
      if (rests[i] == 0) {
        safes.add(i);
        queue.offer(i);
      }
    }

    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (int from : fromLists[node]) {
        --rests[from];
        if (rests[from] == 0) {
          safes.add(from);
          queue.offer(from);
        }
      }
    }

    return safes.stream().sorted().toList();
  }
}
