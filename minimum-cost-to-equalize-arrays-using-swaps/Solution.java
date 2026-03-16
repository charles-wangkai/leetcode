import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minCost(int[] nums1, int[] nums2) {
    Map<Integer, Integer> valueToDiff = new HashMap<>();
    for (int num1 : nums1) {
      valueToDiff.put(num1, valueToDiff.getOrDefault(num1, 0) + 1);
    }
    for (int num2 : nums2) {
      valueToDiff.put(num2, valueToDiff.getOrDefault(num2, 0) - 1);
    }

    if (valueToDiff.values().stream().anyMatch(diff -> diff % 2 != 0)) {
      return -1;
    }

    return valueToDiff.values().stream().filter(diff -> diff > 0).mapToInt(diff -> diff / 2).sum();
  }
}