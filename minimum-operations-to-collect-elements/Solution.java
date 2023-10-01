import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public int minOperations(List<Integer> nums, int k) {
    Set<Integer> seen = new HashSet<>();
    for (int i = nums.size() - 1; ; --i) {
      if (nums.get(i) <= k) {
        seen.add(nums.get(i));
      }

      if (seen.size() == k) {
        return nums.size() - i;
      }
    }
  }
}
