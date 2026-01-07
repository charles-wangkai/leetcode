import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int maxStability(int n, int[][] edges, int k) {
    Pair[] mandatoryPairs =
        Arrays.stream(edges)
            .filter(edge -> edge[3] == 1)
            .map(edge -> new Pair(edge[0], edge[1]))
            .toArray(Pair[]::new);
    if (computeComponentNum(n, mandatoryPairs) != n - mandatoryPairs.length) {
      return -1;
    }

    int result = -1;
    int lower = 0;
    int upper =
        Arrays.stream(edges)
            .filter(edge -> edge[3] == 1)
            .mapToInt(edge -> edge[2])
            .min()
            .orElse(Arrays.stream(edges).mapToInt(edge -> edge[2] * 2).max().getAsInt());
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(n, edges, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int n, int[][] edges, int k, int strengthLimit) {
    if (computeComponentNum(
            n,
            Arrays.stream(edges)
                .filter(edge -> edge[3] == 1 || edge[2] * 2 >= strengthLimit)
                .map(edge -> new Pair(edge[0], edge[1]))
                .toArray(Pair[]::new))
        != 1) {
      return false;
    }

    return computeComponentNum(
                n,
                Arrays.stream(edges)
                    .filter(edge -> edge[3] == 1 || edge[2] >= strengthLimit)
                    .map(edge -> new Pair(edge[0], edge[1]))
                    .toArray(Pair[]::new))
            - 1
        <= k;
  }

  int computeComponentNum(int n, Pair[] pairs) {
    Dsu dsu = new Dsu(n);
    for (Pair pair : pairs) {
      dsu.union(pair.node1(), pair.node2());
    }

    return dsu.buildLeaderToGroup().size();
  }
}

record Pair(int node1, int node2) {}

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
