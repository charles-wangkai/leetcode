import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int specialTriplets(int[] nums) {
    Map<Integer, Integer> leftValueToCount = new HashMap<>();
    int[] leftDoubleNums = new int[nums.length];
    for (int i = 0; i < leftDoubleNums.length; ++i) {
      leftDoubleNums[i] = leftValueToCount.getOrDefault(nums[i] * 2, 0);
      leftValueToCount.put(nums[i], leftValueToCount.getOrDefault(nums[i], 0) + 1);
    }

    Map<Integer, Integer> rightValueToCount = new HashMap<>();
    int[] rightDoubleNums = new int[nums.length];
    for (int i = rightDoubleNums.length - 1; i >= 0; --i) {
      rightDoubleNums[i] = rightValueToCount.getOrDefault(nums[i] * 2, 0);
      rightValueToCount.put(nums[i], rightValueToCount.getOrDefault(nums[i], 0) + 1);
    }

    return (int)
        (IntStream.range(0, nums.length)
                .mapToLong(i -> (long) (leftDoubleNums[i]) * rightDoubleNums[i])
                .sum()
            % MODULUS);
  }
}