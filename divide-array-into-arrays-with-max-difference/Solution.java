import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[][] divideArray(int[] nums, int k) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length / 3).allMatch(i -> nums[i * 3 + 2] - nums[i * 3] <= k)
        ? IntStream.range(0, nums.length / 3)
            .mapToObj(i -> IntStream.range(0, 3).map(j -> nums[i * 3 + j]).toArray())
            .toArray(int[][]::new)
        : new int[0][];
  }
}