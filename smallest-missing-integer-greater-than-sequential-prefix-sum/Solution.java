import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int missingInteger(int[] nums) {
    int prefixSum = 0;
    for (int i = 0; i < nums.length && (i == 0 || nums[i] - nums[i - 1] == 1); ++i) {
      prefixSum += nums[i];
    }

    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    for (int i = prefixSum; ; ++i) {
      if (!set.contains(i)) {
        return i;
      }
    }
  }
}