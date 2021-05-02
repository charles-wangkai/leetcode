import java.util.stream.IntStream;

class Solution {
  public int getMinDistance(int[] nums, int target, int start) {
    return IntStream.range(0, nums.length)
        .filter(i -> nums[i] == target)
        .map(i -> Math.abs(i - start))
        .min()
        .getAsInt();
  }
}
