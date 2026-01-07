import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int minTime(int n, int[][] edges, int k) {
    Map<Integer, List<Integer>> timeToEdgeIndices = new HashMap<>();
    for (int i = 0; i < edges.length; ++i) {
      timeToEdgeIndices.putIfAbsent(edges[i][2], new ArrayList<>());
      timeToEdgeIndices.get(edges[i][2]).add(i);
    }

    int[] sortedTimes =
        timeToEdgeIndices.keySet().stream()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    int result = -1;
    int componentNum = n;
    Dsu dsu = new Dsu(n);
    for (int i = 0; i <= sortedTimes.length; ++i) {
      if (componentNum >= k) {
        result = (i == sortedTimes.length) ? 0 : sortedTimes[i];
      }

      if (i != sortedTimes.length) {
        for (int edgeIndex : timeToEdgeIndices.get(sortedTimes[i])) {
          int leader1 = dsu.find(edges[edgeIndex][0]);
          int leader2 = dsu.find(edges[edgeIndex][1]);
          if (leader1 != leader2) {
            dsu.union(leader1, leader2);
            --componentNum;
          }
        }
      }
    }

    return result;
  }
}

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
