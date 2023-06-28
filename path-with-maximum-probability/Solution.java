import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    Double[] probs = new Double[n];

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::prob).reversed());
    pq.offer(new Element(start, 1));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (probs[head.node()] == null) {
        if (head.node() == end) {
          return head.prob();
        }

        probs[head.node()] = head.prob();

        for (int edge : edgeLists[head.node()]) {
          int other = (head.node() == edges[edge][0]) ? edges[edge][1] : edges[edge][0];
          if (probs[other] == null) {
            pq.offer(new Element(other, head.prob() * succProb[edge]));
          }
        }
      }
    }

    return 0;
  }
}

record Element(int node, double prob) {}
