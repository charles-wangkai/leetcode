import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> permutations = new ArrayList<>();
    search(permutations, nums, 0);

    return permutations;
  }

  void search(List<List<Integer>> permutations, int[] nums, int index) {
    if (index == nums.length) {
      permutations.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));

      return;
    }

    Set<Integer> seen = new HashSet<>();
    for (int i = index; i < nums.length; ++i) {
      if (!seen.contains(nums[i])) {
        seen.add(nums[i]);

        swap(nums, index, i);
        search(permutations, nums, index + 1);
        swap(nums, index, i);
      }
    }
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
