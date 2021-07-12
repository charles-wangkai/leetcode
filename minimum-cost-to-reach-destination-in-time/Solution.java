import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int minCost(int maxTime, int[][] edges, int[] passingFees) {
    int n = passingFees.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeIndexLists = new List[n];
    for (int i = 0; i < edgeIndexLists.length; ++i) {
      edgeIndexLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeIndexLists[edges[i][0]].add(i);
      edgeIndexLists[edges[i][1]].add(i);
    }

    int[][] costs = new int[n][maxTime + 1];
    for (int i = 0; i < costs.length; ++i) {
      Arrays.fill(costs[i], Integer.MAX_VALUE);
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.cost));
    pq.offer(new Element(0, 0, passingFees[0]));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (costs[head.city][head.time] >= 0) {
        costs[head.city][head.time] = -1;
        if (head.city == n - 1) {
          return head.cost;
        }

        for (int edgeIndex : edgeIndexLists[head.city]) {
          int[] edge = edges[edgeIndex];
          int adj = (head.city == edge[0]) ? edge[1] : edge[0];
          if (head.time + edge[2] <= maxTime
              && head.cost + passingFees[adj] < costs[adj][head.time + edge[2]]) {
            costs[adj][head.time + edge[2]] = head.cost + passingFees[adj];
            pq.offer(new Element(adj, head.time + edge[2], head.cost + passingFees[adj]));
          }
        }
      }
    }

    return -1;
  }
}

class Element {
  int city;
  int time;
  int cost;

  Element(int city, int time, int cost) {
    this.city = city;
    this.time = time;
    this.cost = cost;
  }
}
