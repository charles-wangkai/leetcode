import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Set<List<Integer>> result = new HashSet<>();
    Set<Integer> lastValues = new HashSet<>();
    for (int k = nums.length - 2; k >= 0; --k) {
      lastValues.add(nums[k + 1]);
      for (int j = 0; j < k; ++j) {
        for (int i = 0; i < j; ++i) {
          long lastValue = (long) target - nums[i] - nums[j] - nums[k];
          if (lastValue >= Integer.MIN_VALUE
              && lastValue <= Integer.MAX_VALUE
              && lastValues.contains((int) lastValue)) {
            result.add(
                IntStream.of(nums[i], nums[j], nums[k], (int) lastValue).sorted().boxed().toList());
          }
        }
      }
    }

    return List.copyOf(result);
  }
}
