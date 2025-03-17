import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean divideArray(int[] nums) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length / 2).allMatch(i -> nums[i * 2] == nums[i * 2 + 1]);
  }
}