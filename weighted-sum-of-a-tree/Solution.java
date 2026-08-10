import java.util.ArrayList;
import java.util.List;

class Solution {
  public long weightedSum(int[] parent, int[] nums) {
    int n = parent.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[n];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int i = 1; i < parent.length; ++i) {
      childLists[parent[i]].add(i);
    }

    int height = findHeight(childLists, 0, 1);

    return search(nums, childLists, height, 0, 1);
  }

  long search(int[] nums, List<Integer>[] childLists, int height, int node, int depth) {
    long result = nums[node] * (height - depth + 1L);
    for (int child : childLists[node]) {
      result += search(nums, childLists, height, child, depth + 1);
    }

    return result;
  }

  int findHeight(List<Integer>[] childLists, int node, int depth) {
    int result = depth;
    for (int child : childLists[node]) {
      result = Math.max(result, findHeight(childLists, child, depth + 1));
    }

    return result;
  }
}