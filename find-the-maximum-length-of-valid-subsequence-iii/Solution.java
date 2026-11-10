import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumLength(int[] nums, int k) {
    Map<Integer, Integer> remainderToLength = new HashMap<>();
    for (int num : nums) {
      int remainder = num % k;
      remainderToLength.put(
          remainder, remainderToLength.getOrDefault(Math.floorMod(-remainder, k), 0) + 1);
    }

    return remainderToLength.values().stream().mapToInt(Integer::valueOf).max().getAsInt();
  }
}