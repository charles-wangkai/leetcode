import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxFrequency(int[] nums, int k, int numOperations) {
    Arrays.sort(nums);

    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int result = 0;
    int lower = Arrays.stream(nums).min().getAsInt();
    int upper = Arrays.stream(nums).max().getAsInt();
    int beginIndex = 0;
    int endIndex = -1;
    for (int i = lower; i <= upper; ++i) {
      while (beginIndex != nums.length && nums[beginIndex] < i - k) {
        ++beginIndex;
      }
      while (endIndex != nums.length - 1 && nums[endIndex + 1] <= i + k) {
        ++endIndex;
      }

      result =
          Math.max(
              result,
              valueToCount.getOrDefault(i, 0)
                  + Math.min(
                      numOperations, endIndex - beginIndex + 1 - valueToCount.getOrDefault(i, 0)));
    }

    return result;
  }
}