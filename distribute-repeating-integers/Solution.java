import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean canDistribute(int[] nums, int[] quantity) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }
    int[] counts = valueToCount.values().stream().mapToInt(x -> x).toArray();

    int m = quantity.length;
    boolean[][] states = new boolean[counts.length + 1][1 << m];
    states[0][(1 << m) - 1] = true;
    for (int i = 0; i < counts.length; ++i) {
      for (int current = 0; current < 1 << m; ++current) {
        states[i + 1][current] = states[i][current];

        for (int prev = 0; prev < 1 << m; ++prev) {
          if ((prev & current) == current) {
            int delta = prev - current;
            int quantitySum = 0;
            for (int j = 0; j < m; ++j) {
              if ((delta & (1 << j)) != 0) {
                quantitySum += quantity[j];
              }
            }
            if (quantitySum <= counts[i]) {
              states[i + 1][current] |= states[i][prev];
            }
          }
        }
      }
    }

    return states[counts.length][0];
  }
}
