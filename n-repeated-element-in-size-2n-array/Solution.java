import java.util.HashSet;
import java.util.Set;

class Solution {
  public int repeatedNTimes(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int i = 0; ; ++i) {
      if (seen.contains(nums[i])) {
        return nums[i];
      }

      seen.add(nums[i]);
    }
  }
}
