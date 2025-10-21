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
    int beginIndex = 0;
    int endIndex = -1;
    for (int value = nums[0]; value <= nums[nums.length - 1]; ++value) {
      while (beginIndex != nums.length && nums[beginIndex] < value - k) {
        ++beginIndex;
      }
      while (endIndex != nums.length - 1 && nums[endIndex + 1] <= value + k) {
        ++endIndex;
      }

      result =
          Math.max(
              result,
              valueToCount.getOrDefault(value, 0)
                  + Math.min(
                      numOperations,
                      endIndex - beginIndex + 1 - valueToCount.getOrDefault(value, 0)));
    }

    return result;
  }
}