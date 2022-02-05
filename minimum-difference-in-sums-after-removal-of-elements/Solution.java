import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public long minimumDifference(int[] nums) {
    int targetSize = nums.length / 3;

    long[] leftMinSums = new long[nums.length];
    Arrays.fill(leftMinSums, Long.MAX_VALUE);
    PriorityQueue<Integer> mins = new PriorityQueue<>(Comparator.reverseOrder());
    long leftMinSum = 0;
    for (int i = 0; i < leftMinSums.length; ++i) {
      mins.offer(nums[i]);
      leftMinSum += nums[i];
      if (mins.size() == targetSize + 1) {
        leftMinSum -= mins.poll();
      }

      if (mins.size() == targetSize) {
        leftMinSums[i] = leftMinSum;
      }
    }

    long[] rightMaxSums = new long[nums.length];
    Arrays.fill(rightMaxSums, Long.MAX_VALUE);
    PriorityQueue<Integer> maxs = new PriorityQueue<>();
    long rightMaxSum = 0;
    for (int i = rightMaxSums.length - 1; i >= 0; --i) {
      maxs.offer(nums[i]);
      rightMaxSum += nums[i];
      if (maxs.size() == targetSize + 1) {
        rightMaxSum -= maxs.poll();
      }

      if (maxs.size() == targetSize) {
        rightMaxSums[i] = rightMaxSum;
      }
    }

    return IntStream.range(0, nums.length - 1)
        .filter(i -> leftMinSums[i] != Long.MAX_VALUE && rightMaxSums[i + 1] != Long.MAX_VALUE)
        .mapToLong(i -> leftMinSums[i] - rightMaxSums[i + 1])
        .min()
        .getAsLong();
  }
}