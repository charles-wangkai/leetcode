import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
  public int minCostConnectPoints(int[][] points) {
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::cost));
    for (int i = 0; i < points.length; ++i) {
      for (int j = i + 1; j < points.length; ++j) {
        pq.offer(
            new Element(
                i,
                j,
                Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
      }
    }

    int result = 0;
    Dsu dsu = new Dsu(points.length);
    while (!pq.isEmpty()) {
      Element head = pq.poll();

      int leader1 = dsu.find(head.index1());
      int leader2 = dsu.find(head.index2());
      if (leader1 != leader2) {
        result += head.cost();
        dsu.union(leader1, leader2);
      }
    }

    return result;
  }
}

record Element(int index1, int index2, int cost) {}

class Dsu {
  int[] parentOrSizes;

  Dsu(int n) {
    parentOrSizes = new int[n];
    Arrays.fill(parentOrSizes, -1);
  }

  int find(int a) {
    if (parentOrSizes[a] < 0) {
      return a;
    }

    parentOrSizes[a] = find(parentOrSizes[a]);

    return parentOrSizes[a];
  }

  void union(int a, int b) {
    int aLeader = find(a);
    int bLeader = find(b);
    if (aLeader != bLeader) {
      parentOrSizes[aLeader] += parentOrSizes[bLeader];
      parentOrSizes[bLeader] = aLeader;
    }
  }

  int getSize(int a) {
    return -parentOrSizes[find(a)];
  }

  Map<Integer, List<Integer>> buildLeaderToGroup() {
    Map<Integer, List<Integer>> leaderToGroup = new HashMap<>();
    for (int i = 0; i < parentOrSizes.length; ++i) {
      int leader = find(i);
      leaderToGroup.putIfAbsent(leader, new ArrayList<>());
      leaderToGroup.get(leader).add(i);
    }

    return leaderToGroup;
  }
}
