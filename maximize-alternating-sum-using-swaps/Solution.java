import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public long maxAlternatingSum(int[] nums, int[][] swaps) {
    Dsu dsu = new Dsu(nums.length);
    for (int[] swap : swaps) {
      dsu.union(swap[0], swap[1]);
    }

    Map<Integer, List<Integer>> leaderToGroup = dsu.buildLeaderToGroup();

    return leaderToGroup.values().stream()
        .mapToLong(
            group -> {
              int[] sortedValues = group.stream().mapToInt(index -> nums[index]).sorted().toArray();
              int[] sortedFactors =
                  group.stream().mapToInt(index -> (index % 2 == 0) ? 1 : -1).sorted().toArray();

              return IntStream.range(0, sortedValues.length)
                  .map(i -> sortedValues[i] * sortedFactors[i])
                  .asLongStream()
                  .sum();
            })
        .sum();
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
