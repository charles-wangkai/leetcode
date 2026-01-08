import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public String smallestEquivalentString(String s1, String s2, String baseStr) {
    Dsu dsu = new Dsu(26);
    for (int i = 0; i < s1.length(); ++i) {
      int leader1 = dsu.find(s1.charAt(i) - 'a');
      int leader2 = dsu.find(s2.charAt(i) - 'a');
      if (leader1 < leader2) {
        dsu.union(leader1, leader2);
      } else if (leader1 > leader2) {
        dsu.union(leader2, leader1);
      }
    }

    return baseStr
        .chars()
        .mapToObj(c -> (char) (dsu.find(c - 'a') + 'a'))
        .map(String::valueOf)
        .collect(Collectors.joining());
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
