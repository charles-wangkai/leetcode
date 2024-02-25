import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean canTraverseAllPairs(int[] nums) {
    int[] parents = new int[nums.length];
    Arrays.fill(parents, -1);

    int max = Arrays.stream(nums).max().getAsInt();
    for (int d = 2; d * d <= max; ++d) {
      int prevIndex = -1;
      for (int i = 0; i < nums.length; ++i) {
        if (nums[i] % d == 0) {
          if (prevIndex != -1) {
            int root1 = findRoot(parents, prevIndex);
            int root2 = findRoot(parents, i);
            if (root1 != root2) {
              parents[root2] = root1;
            }
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
          int root1 = findRoot(parents, primeToPrevIndex.get(nums[i]));
          int root2 = findRoot(parents, i);
          if (root1 != root2) {
            parents[root2] = root1;
          }
        }

        primeToPrevIndex.put(nums[i], i);
      }
    }

    return Arrays.stream(parents).filter(parent -> parent == -1).count() == 1;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}
