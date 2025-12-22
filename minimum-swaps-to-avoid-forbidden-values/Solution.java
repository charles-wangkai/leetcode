import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minSwaps(int[] nums, int[] forbidden) {
    Map<Integer, Integer> aValueToCount = buildValueToCount(nums);
    Map<Integer, Integer> bValueToCount = buildValueToCount(forbidden);
    if (aValueToCount.keySet().stream()
        .anyMatch(
            value ->
                aValueToCount.get(value) + bValueToCount.getOrDefault(value, 0) > nums.length)) {
      return -1;
    }

    Map<Integer, Integer> sameValueToCount = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == forbidden[i]) {
        sameValueToCount.put(nums[i], sameValueToCount.getOrDefault(nums[i], 0) + 1);
      }
    }
    if (sameValueToCount.isEmpty()) {
      return 0;
    }

    int maxCount = sameValueToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();
    int totalCount = sameValueToCount.values().stream().mapToInt(Integer::intValue).sum();
    int otherCount = totalCount - maxCount;
    if (otherCount >= maxCount) {
      return Math.ceilDiv(totalCount, 2);
    }

    return maxCount;
  }

  Map<Integer, Integer> buildValueToCount(int[] values) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : values) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return valueToCount;
  }
}