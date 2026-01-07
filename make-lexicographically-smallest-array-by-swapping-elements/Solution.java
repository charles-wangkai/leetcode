import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] lexicographicallySmallestArray(int[] nums, int limit) {
    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.comparing(i -> nums[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    Dsu dsu = new Dsu(nums.length);
    for (int i = 0; i < sortedIndices.length - 1; ++i) {
      if (nums[sortedIndices[i + 1]] - nums[sortedIndices[i]] <= limit) {
        dsu.union(sortedIndices[i], sortedIndices[i + 1]);
      }
    }

    Map<Integer, List<Integer>> leaderToGroup = dsu.buildLeaderToGroup();

    int[] result = new int[nums.length];
    for (List<Integer> indices : leaderToGroup.values()) {
      int[] sortedValues = indices.stream().mapToInt(index -> nums[index]).sorted().toArray();
      for (int i = 0; i < indices.size(); ++i) {
        result[indices.get(i)] = sortedValues[i];
      }
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
