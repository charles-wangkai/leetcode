import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public long[] earliestActivationTimes(int n, int[][] edges, int[] requiredSignals) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
    }

    PriorityQueue<Event> pq = new PriorityQueue<>(Comparator.comparing(Event::time));
    pq.offer(new Event(0, 0));

    requiredSignals[0] = 1;

    long[] result = new long[n];
    Arrays.fill(result, -1);

    while (!pq.isEmpty()) {
      Event head = pq.poll();

      --requiredSignals[head.node()];
      if (requiredSignals[head.node()] == 0) {
        result[head.node()] = head.time();

        for (int e : edgeLists[head.node()]) {
          pq.offer(new Event(head.time() + edges[e][2], edges[e][1]));
        }
      }
    }

    return result;
  }
}

record Event(long time, int node) {}
