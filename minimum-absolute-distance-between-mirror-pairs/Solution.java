import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minMirrorPairDistance(int[] nums) {
    int result = Integer.MAX_VALUE;
    Map<Integer, Integer> valueToMinIndex = new HashMap<>();
    for (int i = nums.length - 1; i >= 0; --i) {
      int reversed = reverse(nums[i]);
      if (valueToMinIndex.containsKey(reversed)) {
        result = Math.min(result, valueToMinIndex.get(reversed) - i);
      }

      valueToMinIndex.put(nums[i], i);
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  int reverse(int x) {
    return Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString());
  }
}