import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countNicePairs(int[] nums) {
    Map<Integer, Integer> diffToCount = new HashMap<>();
    for (int num : nums) {
      int diff = num - rev(num);
      diffToCount.put(diff, diffToCount.getOrDefault(diff, 0) + 1);
    }

    return (int)
        (diffToCount.values().stream().mapToLong(count -> count * (count - 1L) / 2).sum()
            % MODULUS);
  }

  int rev(int x) {
    return Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString());
  }
}
