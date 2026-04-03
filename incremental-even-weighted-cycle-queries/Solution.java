import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int numberOfEdgesAdded(int n, int[][] edges) {
    int result = 0;
    Dsu dsu = new Dsu(n);
    for (int[] edge : edges) {
      Outcome outcome1 = dsu.find(edge[0]);
      Outcome outcome2 = dsu.find(edge[1]);
      if (outcome1.leader() != outcome2.leader()) {
        dsu.union(edge[0], edge[1], edge[2] == 1);
        ++result;
      } else if (!(outcome1.inverted() ^ outcome2.inverted() ^ (edge[2] == 1))) {
        ++result;
      }
    }

    return result;
  }
}

class Dsu {
  int[] parentOrSizes;
  boolean[] invs;

  Dsu(int n) {
    parentOrSizes = new int[n];
    Arrays.fill(parentOrSizes, -1);

    invs = new boolean[n];
  }

  Outcome find(int a) {
    if (parentOrSizes[a] < 0) {
      return new Outcome(a, false);
    }

    int parent = parentOrSizes[a];
    Outcome subOutcome = find(parent);

    parentOrSizes[a] = subOutcome.leader();
    invs[a] ^= invs[parent];

    return new Outcome(parentOrSizes[a], invs[a]);
  }

  void union(int a, int b, boolean inverted) {
    Outcome aOutcome = find(a);
    Outcome bOutcome = find(b);
    if (aOutcome.leader() != bOutcome.leader()) {
      parentOrSizes[aOutcome.leader()] += parentOrSizes[bOutcome.leader()];
      parentOrSizes[bOutcome.leader()] = aOutcome.leader();
      invs[bOutcome.leader()] = aOutcome.inverted() ^ bOutcome.inverted() ^ inverted;
    }
  }

  int getSize(int a) {
    return -parentOrSizes[find(a).leader()];
  }

  Map<Integer, List<Integer>> buildLeaderToGroup() {
    Map<Integer, List<Integer>> leaderToGroup = new HashMap<>();
    for (int i = 0; i < parentOrSizes.length; ++i) {
      int leader = find(i).leader();
      leaderToGroup.putIfAbsent(leader, new ArrayList<>());
      leaderToGroup.get(leader).add(i);
    }

    return leaderToGroup;
  }
}

record Outcome(int leader, boolean inverted) {}
