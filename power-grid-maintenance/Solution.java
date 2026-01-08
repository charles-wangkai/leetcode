import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  public int[] processQueries(int c, int[][] connections, int[][] queries) {
    Dsu dsu = new Dsu(c);
    for (int[] connection : connections) {
      dsu.union(connection[0] - 1, connection[1] - 1);
    }

    Map<Integer, SortedSet<Integer>> leaderToNodes = new HashMap<>();
    for (int node = 0; node < c; ++node) {
      int leader = dsu.find(node);
      leaderToNodes.putIfAbsent(leader, new TreeSet<>());
      leaderToNodes.get(leader).add(node);
    }

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      int type = query[0];
      int node = query[1] - 1;

      SortedSet<Integer> nodes = leaderToNodes.get(dsu.find(node));
      if (type == 1) {
        result.add(nodes.isEmpty() ? -1 : ((nodes.contains(node) ? node : nodes.first()) + 1));
      } else {
        nodes.remove(node);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
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
