import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums) {
    return (int)
        IntStream.range(0, nums.length)
            .filter(i -> nums[i] != ((i == 0) ? 1 : nums[i - 1]))
            .count();
  }
}