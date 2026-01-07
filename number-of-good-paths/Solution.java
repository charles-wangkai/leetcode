import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int numberOfGoodPaths(int[] vals, int[][] edges) {
    int n = vals.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    SortedMap<Integer, List<Integer>> valueToIndices = new TreeMap<>();
    for (int i = 0; i < vals.length; ++i) {
      valueToIndices.putIfAbsent(vals[i], new ArrayList<>());
      valueToIndices.get(vals[i]).add(i);
    }

    int result = n;
    boolean[] visited = new boolean[n];
    Dsu dsu = new Dsu(n);
    for (List<Integer> indices : valueToIndices.values()) {
      for (int index : indices) {
        visited[index] = true;
      }
      for (int index : indices) {
        for (int e : edgeLists[index]) {
          int other = (edges[e][0] == index) ? edges[e][1] : edges[e][0];
          if (visited[other]) {
            dsu.union(index, other);
          }
        }
      }

      Map<Integer, Integer> leaderToCount = new HashMap<>();
      for (int index : indices) {
        int leader = dsu.find(index);
        leaderToCount.put(leader, leaderToCount.getOrDefault(leader, 0) + 1);
      }

      result += leaderToCount.values().stream().mapToInt(count -> count * (count - 1) / 2).sum();
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
