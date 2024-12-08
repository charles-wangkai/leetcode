import java.util.stream.IntStream;

class Solution {
  public int[] constructTransformedArray(int[] nums) {
    return IntStream.range(0, nums.length)
        .map(i -> nums[Math.floorMod(i + nums[i], nums.length)])
        .toArray();
  }
}