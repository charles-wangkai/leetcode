import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int evenSumSubgraphs(int[] nums, int[][] edges) {
    return (int)
        IntStream.range(1, 1 << nums.length)
            .filter(
                mask -> {
                  if (IntStream.range(0, nums.length)
                              .filter(i -> ((mask >> i) & 1) == 1)
                              .map(i -> nums[i])
                              .sum()
                          % 2
                      == 1) {
                    return false;
                  }

                  Dsu dsu = new Dsu(nums.length);
                  for (int[] edge : edges) {
                    if (((mask >> edge[0]) & 1) == 1 && ((mask >> edge[1]) & 1) == 1) {
                      dsu.union(edge[0], edge[1]);
                    }
                  }

                  return IntStream.range(0, nums.length)
                          .filter(i -> ((mask >> i) & 1) == 1)
                          .map(dsu::find)
                          .distinct()
                          .count()
                      == 1;
                })
            .count();
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
