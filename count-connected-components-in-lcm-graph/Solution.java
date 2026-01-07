import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int countComponents(int[] nums, int threshold) {
    Map<Integer, List<Integer>> multipleToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      for (int multiple = nums[i]; multiple <= threshold; multiple += nums[i]) {
        multipleToIndices.putIfAbsent(multiple, new ArrayList<>());
        multipleToIndices.get(multiple).add(i);
      }
    }

    Dsu dsu = new Dsu(nums.length);
    for (List<Integer> indices : multipleToIndices.values()) {
      for (int i = 0; i < indices.size() - 1; ++i) {
        dsu.union(indices.get(i), indices.get(i + 1));
      }
    }

    return dsu.buildLeaderToGroup().size();
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
