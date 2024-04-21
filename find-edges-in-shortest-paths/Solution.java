import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public boolean[] findAnswer(int n, int[][] edges) {
    long[] beginDistances = computeDistances(n, edges, 0);
    long[] endDistances = computeDistances(n, edges, n - 1);

    boolean[] result = new boolean[edges.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] =
          beginDistances[n - 1] != Long.MAX_VALUE
              && ((beginDistances[edges[i][0]] != Long.MAX_VALUE
                      && endDistances[edges[i][1]] != Long.MAX_VALUE
                      && beginDistances[edges[i][0]] + endDistances[edges[i][1]] + edges[i][2]
                          == beginDistances[n - 1])
                  || (beginDistances[edges[i][1]] != Long.MAX_VALUE
                      && endDistances[edges[i][0]] != Long.MAX_VALUE
                      && beginDistances[edges[i][1]] + endDistances[edges[i][0]] + edges[i][2]
                          == beginDistances[n - 1]));
    }

    return result;
  }

  long[] computeDistances(int n, int[][] edges, int from) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    long[] result = new long[n];
    Arrays.fill(result, Long.MAX_VALUE);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(from, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (result[head.node()] == Long.MAX_VALUE) {
        result[head.node()] = head.distance();

        for (int edge : edgeLists[head.node()]) {
          int other = (edges[edge][0] == head.node()) ? edges[edge][1] : edges[edge][0];
          if (result[other] == Long.MAX_VALUE) {
            pq.offer(new Element(other, result[head.node()] + edges[edge][2]));
          }
        }
      }
    }

    return result;
  }
}

record Element(int node, long distance) {}
