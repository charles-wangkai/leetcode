import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] edgeLists = new Map[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new HashMap<>();
    }
    for (int[] flight : flights) {
      edgeLists[flight[0]].put(flight[1], flight[2]);
    }

    @SuppressWarnings("unchecked")
    Set<Integer>[] stopCountSeens = new Set[n];
    for (int i = 0; i < stopCountSeens.length; ++i) {
      stopCountSeens[i] = new HashSet<>();
    }
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::cost));
    pq.add(new Element(src, 0, -1));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (head.city() == dst) {
        return head.cost();
      }

      if (!stopCountSeens[head.city()].contains(head.stopCount())) {
        stopCountSeens[head.city()].add(head.stopCount());

        if (head.stopCount() + 1 <= k) {
          for (int to : edgeLists[head.city()].keySet()) {
            pq.offer(
                new Element(
                    to, head.cost() + edgeLists[head.city()].get(to), head.stopCount() + 1));
          }
        }
      }
    }

    return -1;
  }
}

record Element(int city, int cost, int stopCount) {}
