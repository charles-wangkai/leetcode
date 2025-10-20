import java.util.HashSet;
import java.util.Set;

class Solution {
  public int longestBalanced(int[] nums) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      Set<Integer> evens = new HashSet<>();
      Set<Integer> odds = new HashSet<>();
      for (int j = i; j < nums.length; ++j) {
        ((nums[j] % 2 == 0) ? evens : odds).add(nums[j]);

        if (evens.size() == odds.size()) {
          result = Math.max(result, j - i + 1);
        }
      }
    }

    return result;
  }
}