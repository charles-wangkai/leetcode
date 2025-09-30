import java.util.stream.IntStream;

class Solution {
  public int triangularSum(int[] nums) {
    return (nums.length == 1)
        ? nums[0]
        : triangularSum(
            IntStream.range(0, nums.length - 1).map(i -> (nums[i] + nums[i + 1]) % 10).toArray());
  }
}