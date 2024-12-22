import java.util.stream.IntStream;

class Solution {
  public int countSubarrays(int[] nums) {
    return (int)
        IntStream.rangeClosed(0, nums.length - 3)
            .filter(i -> (nums[i] + nums[i + 2]) * 2 == nums[i + 1])
            .count();
  }
}