import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] numberGame(int[] nums) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length).map(i -> nums[i / 2 * 2 + (1 - i % 2)]).toArray();
  }
}