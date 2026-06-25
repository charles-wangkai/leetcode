import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int shortestPath(int n, int[][] edges, String labels, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
    }

    int[][] distances = new int[n][k + 1];
    for (int i = 0; i < distances.length; ++i) {
      Arrays.fill(distances[i], Integer.MAX_VALUE);
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(0, 1, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node()][head.consecutive()] == Integer.MAX_VALUE) {
        distances[head.node()][head.consecutive()] = head.distance();

        for (int edge : edgeLists[head.node()]) {
          int nextNode = edges[edge][1];
          int nextConsecutive =
              (labels.charAt(nextNode) == labels.charAt(head.node()))
                  ? (head.consecutive() + 1)
                  : 1;
          if (nextConsecutive <= k && distances[nextNode][nextConsecutive] == Integer.MAX_VALUE) {
            pq.offer(new Element(nextNode, nextConsecutive, head.distance() + edges[edge][2]));
          }
        }
      }
    }

    int result = Arrays.stream(distances[n - 1]).min().getAsInt();

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}

record Element(int node, int consecutive, int distance) {}
