import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxScore(int[] nums) {
    return Arrays.stream(nums).sum()
        - ((nums.length % 2 == 0)
            ? IntStream.range(0, nums.length - 1).map(i -> nums[i] + nums[i + 1]).min().getAsInt()
            : Arrays.stream(nums).min().getAsInt());
  }
}