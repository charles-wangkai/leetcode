import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long splitArray(int[] nums) {
    int maxValue = Arrays.stream(nums).max().getAsInt();
    int index =
        IntStream.range(0, nums.length).filter(i -> nums[i] == maxValue).findFirst().getAsInt();

    long result = Math.min(computeDiff(nums, index), computeDiff(nums, index - 1));

    return (result == Long.MAX_VALUE) ? -1 : result;
  }

  long computeDiff(int[] nums, int endIndex) {
    if (!(endIndex >= 0
        && endIndex < nums.length - 1
        && IntStream.rangeClosed(0, endIndex - 1).allMatch(i -> nums[i] < nums[i + 1])
        && IntStream.range(endIndex + 1, nums.length - 1).allMatch(i -> nums[i] > nums[i + 1]))) {
      return Long.MAX_VALUE;
    }

    return Math.abs(
        IntStream.rangeClosed(0, endIndex).map(i -> nums[i]).asLongStream().sum()
            - IntStream.range(endIndex + 1, nums.length).map(i -> nums[i]).asLongStream().sum());
  }
}