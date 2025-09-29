import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public long maxAlternatingSum(int[] nums, int[][] swaps) {
    int[] parents = new int[nums.length];
    Arrays.fill(parents, -1);

    for (int[] swap : swaps) {
      int root1 = findRoot(parents, swap[0]);
      int root2 = findRoot(parents, swap[1]);
      if (root1 != root2) {
        parents[root2] = root1;
      }
    }

    Map<Integer, List<Integer>> rootToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int root = findRoot(parents, i);
      rootToIndices.putIfAbsent(root, new ArrayList<>());
      rootToIndices.get(root).add(i);
    }

    return rootToIndices.values().stream()
        .mapToLong(
            indices -> {
              int[] sortedValues =
                  indices.stream().mapToInt(index -> nums[index]).sorted().toArray();
              int[] sortedFactors =
                  indices.stream().mapToInt(index -> (index % 2 == 0) ? 1 : -1).sorted().toArray();

              return IntStream.range(0, sortedValues.length)
                  .map(i -> sortedValues[i] * sortedFactors[i])
                  .asLongStream()
                  .sum();
            })
        .sum();
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}