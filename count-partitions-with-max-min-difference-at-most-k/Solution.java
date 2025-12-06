import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countPartitions(int[] nums, int k) {
    int[] dp = new int[nums.length + 1];
    dp[0] = 1;

    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    int beginIndex = 0;
    int sum = 0;
    for (int i = 0; i < nums.length; ++i) {
      while (!valueToCount.isEmpty()
          && Math.max(
                  Math.abs(nums[i] - valueToCount.firstKey()),
                  Math.abs(nums[i] - valueToCount.lastKey()))
              > k) {
        updateMap(valueToCount, nums[beginIndex], -1);
        sum = addMod(sum, -dp[beginIndex]);

        ++beginIndex;
      }

      updateMap(valueToCount, nums[i], 1);
      sum = addMod(sum, dp[i]);

      dp[i + 1] = sum;
    }

    return dp[dp.length - 1];
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}