import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);

    List<List<Integer>> subsets = new ArrayList<>();
    search(subsets, nums, new ArrayList<>(), 0, Integer.MIN_VALUE);

    return subsets;
  }

  void search(List<List<Integer>> subsets, int[] nums, List<Integer> subset, int index, int lower) {
    if (index == nums.length) {
      subsets.add(List.copyOf(subset));

      return;
    }

    search(subsets, nums, subset, index + 1, nums[index] + 1);

    if (nums[index] >= lower) {
      subset.add(nums[index]);
      search(subsets, nums, subset, index + 1, nums[index]);
      subset.remove(subset.size() - 1);
    }
  }
}
