import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
  public int countCompleteSubarrays(int[] nums) {
    int result = 0;
    int distinctNum = (int) Arrays.stream(nums).distinct().count();
    for (int i = 0; i < nums.length; ++i) {
      Set<Integer> seen = new HashSet<>();
      for (int j = i; j < nums.length; ++j) {
        seen.add(nums[j]);
        if (seen.size() == distinctNum) {
          ++result;
        }
      }
    }

    return result;
  }
}
