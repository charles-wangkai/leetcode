import java.util.stream.IntStream;

class Solution {
  public int subarraySum(int[] nums) {
    return IntStream.range(0, nums.length)
        .map(i -> IntStream.rangeClosed(Math.max(0, i - nums[i]), i).map(j -> nums[j]).sum())
        .sum();
  }
}