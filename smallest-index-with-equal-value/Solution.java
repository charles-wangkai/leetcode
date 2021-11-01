import java.util.stream.IntStream;

class Solution {
  public int smallestEqual(int[] nums) {
    return IntStream.range(0, nums.length).filter(i -> i % 10 == nums[i]).min().orElse(-1);
  }
}
