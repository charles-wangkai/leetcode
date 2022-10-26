import java.util.HashSet;
import java.util.Set;

class Solution {
  public boolean checkSubarraySum(int[] nums, int k) {
    Set<Integer> seen = new HashSet<>();

    int curr = 0;
    int prev = 0;
    for (int i = 0; i < nums.length; ++i) {
      curr = addMod(curr, nums[i], k);
      if (seen.contains(curr)) {
        return true;
      }

      seen.add(prev);
      prev = curr;
    }

    return false;
  }

  int addMod(int x, int y, int m) {
    return Math.floorMod(x + y, m);
  }
}
