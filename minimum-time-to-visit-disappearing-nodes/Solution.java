import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int[] minimumTime(int n, int[][] edges, int[] disappear) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    int[] result = new int[n];
    Arrays.fill(result, -1);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::time));
    pq.offer(new Element(0, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (result[head.node()] == -1 && head.time() < disappear[head.node()]) {
        result[head.node()] = head.time();

        for (int edge : edgeLists[head.node()]) {
          int other = (head.node() == edges[edge][0]) ? edges[edge][1] : edges[edge][0];
          pq.offer(new Element(other, head.time() + edges[edge][2]));
        }
      }
    }

    return result;
  }
}

record Element(int node, int time) {}
