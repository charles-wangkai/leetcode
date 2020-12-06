import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxOperations(int[] nums, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int result = 0;
    for (int num : nums) {
      int other = k - num;
      if ((other == num && valueToCount.getOrDefault(num, 0) >= 2)
          || (other != num
              && valueToCount.getOrDefault(num, 0) != 0
              && valueToCount.getOrDefault(other, 0) != 0)) {
        ++result;
        valueToCount.put(num, valueToCount.get(num) - 1);
        valueToCount.put(other, valueToCount.get(other) - 1);
      }
    }

    return result;
  }
}
