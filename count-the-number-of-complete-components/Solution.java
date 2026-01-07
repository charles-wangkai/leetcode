import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int countCompleteComponents(int n, int[][] edges) {
    int[] degreeSums = new int[n];
    for (int[] edge : edges) {
      ++degreeSums[edge[0]];
      ++degreeSums[edge[1]];
    }

    Dsu dsu = new Dsu(n);
    for (int[] edge : edges) {
      int leader1 = dsu.find(edge[0]);
      int leader2 = dsu.find(edge[1]);
      if (leader1 != leader2) {
        degreeSums[leader1] += degreeSums[leader2];
        dsu.union(leader1, leader2);
      }
    }

    return (int)
        IntStream.range(0, n)
            .filter(i -> dsu.find(i) == i && dsu.getSize(i) * (dsu.getSize(i) - 1) == degreeSums[i])
            .count();
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
