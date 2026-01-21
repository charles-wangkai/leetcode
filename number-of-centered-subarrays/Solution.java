import java.util.HashSet;
import java.util.Set;

class Solution {
  public int centeredSubarrays(int[] nums) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      int sum = 0;
      Set<Integer> seen = new HashSet<>();
      for (int j = i; j < nums.length; ++j) {
        sum += nums[j];
        seen.add(nums[j]);

        if (seen.contains(sum)) {
          ++result;
        }
      }
    }

    return result;
  }
}