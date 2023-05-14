import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int matrixSum(int[][] nums) {
    Arrays.stream(nums).forEach(Arrays::sort);

    return IntStream.range(0, nums[0].length)
        .map(c -> IntStream.range(0, nums.length).map(r -> nums[r][c]).max().getAsInt())
        .sum();
  }
}
