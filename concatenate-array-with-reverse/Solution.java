import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] concatWithReverse(int[] nums) {
    return IntStream.concat(
            Arrays.stream(nums),
            IntStream.range(0, nums.length).map(i -> nums[nums.length - 1 - i]))
        .toArray();
  }
}