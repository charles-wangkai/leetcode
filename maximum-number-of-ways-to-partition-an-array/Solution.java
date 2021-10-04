import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int waysToPartition(int[] nums, int k) {
    int n = nums.length;

    long[] prefixSums = new long[n];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + nums[i];
    }

    long sum = Arrays.stream(nums).asLongStream().sum();

    int[] leftNums = new int[n];
    long leftSum = 0;
    Map<Long, Integer> leftSumToCount = new HashMap<>();
    for (int i = 0; i < n; ++i) {
      long changedSum = sum - nums[i] + k;
      if (changedSum % 2 == 0) {
        leftNums[i] = leftSumToCount.getOrDefault(changedSum / 2, 0);
      }

      leftSum += nums[i];
      updateMap(leftSumToCount, leftSum, 1);
    }

    int[] rightNums = new int[n];
    long rightSum = 0;
    Map<Long, Integer> rightSumToCount = new HashMap<>();
    for (int i = n - 1; i >= 0; --i) {
      long changedSum = sum - nums[i] + k;
      if (changedSum % 2 == 0) {
        rightNums[i] = rightSumToCount.getOrDefault(changedSum / 2, 0);
      }

      rightSum += nums[i];
      updateMap(rightSumToCount, rightSum, 1);
    }

    return Math.max(
        (int) IntStream.range(0, n - 1).filter(i -> prefixSums[i] * 2 == sum).count(),
        IntStream.range(0, n).map(i -> leftNums[i] + rightNums[i]).max().getAsInt());
  }

  void updateMap(Map<Long, Integer> map, long key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
  }
}
