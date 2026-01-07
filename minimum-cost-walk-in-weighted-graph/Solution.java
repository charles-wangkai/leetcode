import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int[] minimumCost(int n, int[][] edges, int[][] query) {
    int[] subtreeAnds = new int[n];
    Arrays.fill(subtreeAnds, -1);

    Dsu dsu = new Dsu(n);
    for (int[] edge : edges) {
      int leader1 = dsu.find(edge[0]);
      int leader2 = dsu.find(edge[1]);
      if (leader1 != leader2) {
        dsu.union(leader1, leader2);
        subtreeAnds[leader1] = and(subtreeAnds[leader1], subtreeAnds[leader2]);
      }

      subtreeAnds[leader1] = and(subtreeAnds[leader1], edge[2]);
    }

    return Arrays.stream(query)
        .mapToInt(
            q -> {
              int leader1 = dsu.find(q[0]);
              int leader2 = dsu.find(q[1]);

              return (leader1 == leader2) ? subtreeAnds[leader1] : -1;
            })
        .toArray();
  }

  int and(int x, int y) {
    if (x == -1) {
      return y;
    }
    if (y == -1) {
      return x;
    }

    return x & y;
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
