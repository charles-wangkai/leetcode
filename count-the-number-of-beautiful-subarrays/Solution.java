import java.util.HashMap;
import java.util.Map;

class Solution {
  public long beautifulSubarrays(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    valueToCount.put(0, 1);
    int xor = 0;
    for (int num : nums) {
      xor ^= num;
      valueToCount.put(xor, valueToCount.getOrDefault(xor, 0) + 1);
    }

    return valueToCount.values().stream().mapToLong(count -> count * (count - 1L) / 2).sum();
  }
}
