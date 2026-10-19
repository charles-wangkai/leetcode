import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> targetIndices(int[] nums, int target) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length).filter(i -> nums[i] == target).boxed().toList();
  }
}
