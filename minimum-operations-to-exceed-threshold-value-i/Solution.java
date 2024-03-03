import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums, int k) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length).filter(i -> nums[i] >= k).findFirst().getAsInt();
  }
}