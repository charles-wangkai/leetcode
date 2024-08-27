import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public double maxProbability(
      int n, int[][] edges, double[] succProb, int start_node, int end_node) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    boolean[] visited = new boolean[n];

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::prob).reversed());
    pq.offer(new Element(start_node, 1));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (!visited[head.node()]) {
        if (head.node() == end_node) {
          return head.prob();
        }

        visited[head.node()] = true;

        for (int edge : edgeLists[head.node()]) {
          int other = (head.node() == edges[edge][0]) ? edges[edge][1] : edges[edge][0];
          if (!visited[other]) {
            pq.offer(new Element(other, head.prob() * succProb[edge]));
          }
        }
      }
    }

    return 0;
  }
}

record Element(int node, double prob) {}
