import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int findValueOfPartition(int[] nums) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length - 1).map(i -> nums[i + 1] - nums[i]).min().getAsInt();
  }
}
