import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int maxFrequency(int[] nums, int k, int numOperations) {
    Arrays.sort(nums);

    return Math.max(
        computeMaxFreqForExisting(nums, k, numOperations),
        computeMaxFreqForNotExisting(nums, k, numOperations));
  }

  int computeMaxFreqForNotExisting(int[] nums, int k, int numOperations) {
    int result = -1;
    int lower = 1;
    int upper = numOperations;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int k, int size) {
    return IntStream.rangeClosed(0, nums.length - size)
        .anyMatch(i -> nums[i + size - 1] - nums[i] <= 2 * k);
  }

  int computeMaxFreqForExisting(int[] nums, int k, int numOperations) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int result = 0;
    int beginIndex = 0;
    int endIndex = -1;
    for (int target : nums) {
      while (beginIndex != nums.length && nums[beginIndex] < target - k) {
        ++beginIndex;
      }
      while (endIndex != nums.length - 1 && nums[endIndex + 1] <= target + k) {
        ++endIndex;
      }

      result =
          Math.max(
              result,
              valueToCount.get(target)
                  + Math.min(numOperations, endIndex - beginIndex + 1 - valueToCount.get(target)));
    }

    return result;
  }
}