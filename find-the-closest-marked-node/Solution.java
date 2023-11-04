import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.size(); ++i) {
      edgeLists[edges.get(i).get(0)].add(i);
    }

    int[] distances = new int[n];
    Arrays.fill(distances, -1);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(s, 0));

    Set<Integer> markedSet = Arrays.stream(marked).boxed().collect(Collectors.toSet());
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (markedSet.contains(head.node())) {
        return head.distance();
      }

      if (distances[head.node()] == -1) {
        distances[head.node()] = head.distance();

        for (int e : edgeLists[head.node()]) {
          if (distances[edges.get(e).get(1)] == -1) {
            pq.offer(new Element(edges.get(e).get(1), head.distance() + edges.get(e).get(2)));
          }
        }
      }
    }

    return -1;
  }
}

record Element(int node, int distance) {}
