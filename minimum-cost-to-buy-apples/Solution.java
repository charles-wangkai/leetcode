import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public long[] minCost(int n, int[][] roads, int[] appleCost, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] roadLists = new List[n];
    for (int i = 0; i < roadLists.length; ++i) {
      roadLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < roads.length; ++i) {
      roadLists[roads[i][0] - 1].add(i);
      roadLists[roads[i][1] - 1].add(i);
    }

    return IntStream.range(0, n)
        .mapToLong(i -> computeCost(roads, appleCost, k, roadLists, i))
        .toArray();
  }

  long computeCost(int[][] roads, int[] appleCost, int k, List<Integer>[] roadLists, int start) {
    int[] distances = new int[roadLists.length];
    Arrays.fill(distances, -1);
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(start, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node()] == -1) {
        distances[head.node()] = head.distance();

        for (int road : roadLists[head.node()]) {
          int nextNode =
              (head.node() == roads[road][0] - 1) ? (roads[road][1] - 1) : (roads[road][0] - 1);
          if (distances[nextNode] == -1) {
            pq.offer(new Element(nextNode, head.distance() + roads[road][2]));
          }
        }
      }
    }

    return IntStream.range(0, roadLists.length)
        .filter(i -> distances[i] != -1)
        .mapToLong(i -> distances[i] * (k + 1L) + appleCost[i])
        .min()
        .getAsLong();
  }
}

record Element(int node, int distance) {}
