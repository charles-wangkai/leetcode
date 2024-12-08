import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int countComponents(int[] nums, int threshold) {
    Map<Integer, List<Integer>> multipleToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      for (int multiple = nums[i]; multiple <= threshold; multiple += nums[i]) {
        multipleToIndices.putIfAbsent(multiple, new ArrayList<>());
        multipleToIndices.get(multiple).add(i);
      }
    }

    int[] parents = new int[nums.length];
    Arrays.fill(parents, -1);
    for (List<Integer> indices : multipleToIndices.values()) {
      for (int i = 0; i < indices.size() - 1; ++i) {
        int root1 = findRoot(parents, indices.get(i));
        int root2 = findRoot(parents, indices.get(i + 1));
        if (root1 != root2) {
          parents[root2] = root1;
        }
      }
    }

    return (int)
        IntStream.range(0, parents.length).map(i -> findRoot(parents, i)).distinct().count();
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}