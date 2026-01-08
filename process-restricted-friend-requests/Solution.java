import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
    boolean[] result = new boolean[requests.length];
    Dsu dsu = new Dsu(n);
    for (int i = 0; i < result.length; ++i) {
      int leader1 = dsu.find(requests[i][0]);
      int leader2 = dsu.find(requests[i][1]);
      if (leader1 == leader2) {
        result[i] = true;
      } else if (check(dsu, restrictions, leader1, leader2)) {
        dsu.union(leader1, leader2);
        result[i] = true;
      }
    }

    return result;
  }

  boolean check(Dsu dsu, int[][] restrictions, int leader1, int leader2) {
    return Arrays.stream(restrictions)
        .allMatch(
            restriction -> {
              int l1 = dsu.find(restriction[0]);
              int l2 = dsu.find(restriction[1]);

              return !((l1 == leader1 && l2 == leader2) || (l1 == leader2 && l2 == leader1));
            });
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
