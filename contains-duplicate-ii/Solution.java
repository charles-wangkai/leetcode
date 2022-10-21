import java.util.HashSet;
import java.util.Set;

class Solution {
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> seen = new HashSet<>();
    int beginIndex = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (i - beginIndex == k + 1) {
        seen.remove(nums[beginIndex]);
        ++beginIndex;
      }
      if (seen.contains(nums[i])) {
        return true;
      }

      seen.add(nums[i]);
    }

    return false;
  }
}
