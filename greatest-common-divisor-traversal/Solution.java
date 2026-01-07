import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public boolean canTraverseAllPairs(int[] nums) {
    Dsu dsu = new Dsu(nums.length);
    int max = Arrays.stream(nums).max().getAsInt();
    for (int d = 2; d * d <= max; ++d) {
      int prevIndex = -1;
      for (int i = 0; i < nums.length; ++i) {
        if (nums[i] % d == 0) {
          if (prevIndex != -1) {
            dsu.union(prevIndex, i);
          }

          while (nums[i] % d == 0) {
            nums[i] /= d;
          }

          prevIndex = i;
        }
      }
    }

    Map<Integer, Integer> primeToPrevIndex = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] != 1) {
        if (primeToPrevIndex.containsKey(nums[i])) {
          dsu.union(primeToPrevIndex.get(nums[i]), i);
        }

        primeToPrevIndex.put(nums[i], i);
      }
    }

    return dsu.buildLeaderToGroup().size() == 1;
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
