import java.util.Arrays;

class Solution {
  public int maxFrequency(int[] nums, int k) {
    return (int) Arrays.stream(nums).filter(value -> value == k).count()
        + Arrays.stream(nums)
            .distinct()
            .filter(value -> value != k)
            .map(value -> computeMaxFreq(nums, k, value))
            .max()
            .orElse(0);
  }

  static int computeMaxFreq(int[] nums, int k, int value) {
    return computeMaxSubarraySum(
        Arrays.stream(nums)
            .map(
                x -> {
                  if (x == value) {
                    return 1;
                  }
                  if (x == k) {
                    return -1;
                  }

                  return 0;
                })
            .toArray());
  }

  static int computeMaxSubarraySum(int[] a) {
    int result = 0;
    int sum = 0;
    for (int ai : a) {
      sum += ai;
      result = Math.max(result, sum);
      sum = Math.max(sum, 0);
    }

    return result;
  }
}