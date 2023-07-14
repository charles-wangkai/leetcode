import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestSubsequence(int[] arr, int difference) {
    Map<Integer, Integer> lastNumberToLength = new HashMap<>();
    for (int value : arr) {
      lastNumberToLength.put(value, lastNumberToLength.getOrDefault(value - difference, 0) + 1);
    }

    return lastNumberToLength.values().stream().mapToInt(Integer::intValue).max().getAsInt();
  }
}
