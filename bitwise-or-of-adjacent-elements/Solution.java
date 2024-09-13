import java.util.stream.IntStream;

class Solution {
  public int[] orArray(int[] nums) {
    return IntStream.range(0, nums.length - 1).map(i -> nums[i] | nums[i + 1]).toArray();
  }
}