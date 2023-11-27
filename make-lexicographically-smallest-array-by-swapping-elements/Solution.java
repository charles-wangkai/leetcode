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

    int[] parents = new int[nums.length];
    Arrays.fill(parents, -1);
    for (int i = 0; i < sortedIndices.length - 1; ++i) {
      if (nums[sortedIndices[i + 1]] - nums[sortedIndices[i]] <= limit) {
        int root1 = findRoot(parents, sortedIndices[i]);
        int root2 = findRoot(parents, sortedIndices[i + 1]);
        if (root1 != root2) {
          parents[root2] = root1;
        }
      }
    }

    Map<Integer, List<Integer>> rootToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int root = findRoot(parents, i);
      rootToIndices.putIfAbsent(root, new ArrayList<>());
      rootToIndices.get(root).add(i);
    }

    int[] result = new int[nums.length];
    for (List<Integer> indices : rootToIndices.values()) {
      int[] sortedValues = indices.stream().mapToInt(index -> nums[index]).sorted().toArray();
      for (int i = 0; i < indices.size(); ++i) {
        result[indices.get(i)] = sortedValues[i];
      }
    }

    return result;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}