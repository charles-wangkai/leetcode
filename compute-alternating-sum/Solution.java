import java.util.stream.IntStream;

class Solution {
  public int alternatingSum(int[] nums) {
    return IntStream.range(0, nums.length).map(i -> nums[i] * ((i % 2 == 0) ? 1 : -1)).sum();
  }
}