import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumSwaps(int[] nums) {
    int zeroCount = (int) Arrays.stream(nums).filter(x -> x == 0).count();

    return (int)
        IntStream.range(nums.length - zeroCount, nums.length).filter(i -> nums[i] != 0).count();
  }
}