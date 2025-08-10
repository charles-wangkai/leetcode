import java.util.stream.IntStream;

class Solution {
  public int sortPermutation(int[] nums) {
    return IntStream.range(0, nums.length)
        .filter(i -> nums[i] != i)
        .map(i -> nums[i])
        .reduce((acc, x) -> acc & x)
        .orElse(0);
  }
}