import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int numberOfComponents(int[][] properties, int k) {
    int n = properties.length;

    Dsu dsu = new Dsu(n);
    for (int i = 0; i < properties.length; ++i) {
      for (int j = i + 1; j < properties.length; ++j) {
        if (intersect(properties[i], properties[j]) >= k) {
          dsu.union(i, j);
        }
      }
    }

    return dsu.buildLeaderToGroup().size();
  }

  int intersect(int[] a, int[] b) {
    Set<Integer> aValues = Arrays.stream(a).boxed().collect(Collectors.toSet());

    return (int) Arrays.stream(b).filter(aValues::contains).distinct().count();
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
