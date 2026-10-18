import java.util.stream.IntStream;

class Solution {
  public int minDeletions(int[] nums) {
    return (int) IntStream.range(1, nums.length).filter(i -> nums[i] == nums[i - 1]).count();
  }
}