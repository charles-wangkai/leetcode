import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int networkDelayTime(int[][] times, int n, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeIndices = new List[n];
    for (int i = 0; i < edgeIndices.length; ++i) {
      edgeIndices[i] = new ArrayList<>();
    }
    for (int i = 0; i < times.length; ++i) {
      edgeIndices[times[i][0] - 1].add(i);
    }

    boolean[] visited = new boolean[n];
    int rest = n;
    int lastTime = -1;
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.time));
    pq.offer(new Element(k - 1, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (visited[head.node]) {
        continue;
      }

      visited[head.node] = true;
      lastTime = head.time;
      --rest;

      for (int edgeIndex : edgeIndices[head.node]) {
        pq.offer(new Element(times[edgeIndex][1] - 1, head.time + times[edgeIndex][2]));
      }
    }

    return (rest == 0) ? lastTime : -1;
  }
}

class Element {
  int node;
  int time;

  Element(int node, int time) {
    this.node = node;
    this.time = time;
  }
}