import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < flights.length; ++i) {
      edgeLists[flights[i][0]].add(i);
    }

    @SuppressWarnings("unchecked")
    Set<Integer>[] stopCountSeens = new Set[n];
    for (int i = 0; i < stopCountSeens.length; ++i) {
      stopCountSeens[i] = new HashSet<>();
    }
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::cost));
    pq.add(new Element(src, -1, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (head.city() == dst) {
        return head.cost();
      }

      if (!stopCountSeens[head.city()].contains(head.stopCount())) {
        stopCountSeens[head.city()].add(head.stopCount());

        if (head.stopCount() + 1 <= k) {
          for (int edge : edgeLists[head.city()]) {
            pq.offer(
                new Element(
                    flights[edge][1], head.stopCount() + 1, head.cost() + flights[edge][2]));
          }
        }
      }
    }

    return -1;
  }
}

record Element(int city, int stopCount, int cost) {}
