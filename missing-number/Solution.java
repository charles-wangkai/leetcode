import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int missingNumber(int[] nums) {
    return IntStream.rangeClosed(0, nums.length).sum() - Arrays.stream(nums).sum();
  }
}
