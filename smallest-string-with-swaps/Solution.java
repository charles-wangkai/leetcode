import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
    Dsu dsu = new Dsu(s.length());
    for (List<Integer> pair : pairs) {
      dsu.union(pair.get(0), pair.get(1));
    }

    char[] result = new char[s.length()];
    for (List<Integer> group : dsu.buildLeaderToGroup().values()) {
      List<Character> letters = group.stream().map(s::charAt).sorted().toList();

      for (int i = 0; i < group.size(); ++i) {
        result[group.get(i)] = letters.get(i);
      }
    }

    return String.valueOf(result);
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
