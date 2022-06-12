import java.util.HashSet;
import java.util.Set;

class Solution {
  public int maximumUniqueSubarray(int[] nums) {
    int result = 0;
    int beginIndex = 0;
    Set<Integer> seen = new HashSet<>();
    int sum = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      while (seen.contains(nums[endIndex])) {
        sum -= nums[beginIndex];
        seen.remove(nums[beginIndex]);
        ++beginIndex;
      }

      sum += nums[endIndex];
      seen.add(nums[endIndex]);

      result = Math.max(result, sum);
    }

    return result;
  }
}
