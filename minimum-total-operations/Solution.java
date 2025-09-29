import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums) {
    return (int) IntStream.range(0, nums.length - 1).filter(i -> nums[i] != nums[i + 1]).count();
  }
}