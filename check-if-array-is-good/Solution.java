import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean isGood(int[] nums) {
    return Arrays.equals(
        Arrays.stream(nums).sorted().toArray(),
        IntStream.range(0, nums.length).map(i -> Math.min(nums.length - 1, i + 1)).toArray());
  }
}
