import java.util.stream.IntStream;

class Solution {
  public int[] buildArray(int[] nums) {
    return IntStream.range(0, nums.length).map(i -> nums[nums[i]]).toArray();
  }
}
