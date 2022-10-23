import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long minCost(int[] nums, int[] cost) {
    int n = nums.length;

    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.comparing(i -> nums[i]))
            .mapToInt(x -> x)
            .toArray();

    long result = Long.MAX_VALUE;
    long leftSum = 0;
    long leftCost = 0;
    long rightSum = Arrays.stream(cost).asLongStream().sum();
    long rightCost =
        IntStream.range(0, n)
            .mapToLong(i -> (long) (nums[i] - nums[sortedIndices[0]]) * cost[i])
            .sum();
    for (int i = 0; i < n; ++i) {
      int prev = nums[sortedIndices[(i == 0) ? 0 : (i - 1)]];
      leftCost += (long) leftSum * (nums[sortedIndices[i]] - prev);
      leftSum += cost[sortedIndices[i]];
      rightCost -= (long) rightSum * (nums[sortedIndices[i]] - prev);
      rightSum -= cost[sortedIndices[i]];

      result = Math.min(result, leftCost + rightCost);
    }

    return result;
  }
}
