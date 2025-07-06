import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int minTime(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
    }

    int[] times = new int[n];
    Arrays.fill(times, -1);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::time));
    pq.offer(new Element(0, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (times[head.node()] == -1) {
        times[head.node()] = head.time();
        for (int e : edgeLists[head.node()]) {
          if (times[head.node()] <= edges[e][3] && times[edges[e][1]] == -1) {
            pq.offer(new Element(edges[e][1], Math.max(times[head.node()], edges[e][2]) + 1));
          }
        }
      }
    }

    return times[n - 1];
  }
}

record Element(int node, int time) {}
