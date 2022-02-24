import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int minimumCost(int n, int[][] highways, int discounts) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < highways.length; ++i) {
      edgeLists[highways[i][0]].add(i);
      edgeLists[highways[i][1]].add(i);
    }

    int[][] distances = new int[n][discounts + 1];
    boolean[][] visited = new boolean[n][discounts + 1];
    for (int i = 0; i < distances.length; ++i) {
      Arrays.fill(distances[i], Integer.MAX_VALUE);
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.distance));
    pq.offer(new Element(0, 0, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (!visited[head.city][head.discountNum]) {
        visited[head.city][head.discountNum] = true;

        for (int edge : edgeLists[head.city]) {
          int other = (highways[edge][0] == head.city) ? highways[edge][1] : highways[edge][0];

          if (head.distance + highways[edge][2] < distances[other][head.discountNum]) {
            distances[other][head.discountNum] = head.distance + highways[edge][2];
            pq.offer(new Element(other, head.discountNum, head.distance + highways[edge][2]));
          }

          if (head.discountNum != discounts
              && head.distance + highways[edge][2] / 2 < distances[other][head.discountNum + 1]) {
            distances[other][head.discountNum + 1] = head.distance + highways[edge][2] / 2;
            pq.offer(
                new Element(other, head.discountNum + 1, head.distance + highways[edge][2] / 2));
          }
        }
      }
    }

    int minDistance = Arrays.stream(distances[n - 1]).min().getAsInt();

    return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
  }
}

class Element {
  int city;
  int discountNum;
  int distance;

  Element(int city, int discountNum, int distance) {
    this.city = city;
    this.discountNum = discountNum;
    this.distance = distance;
  }
}