import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int duplicateNumbersXOR(int[] nums) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length - 1)
        .filter(i -> nums[i] == nums[i + 1])
        .map(i -> nums[i])
        .reduce((acc, x) -> acc ^ x)
        .orElse(0);
  }
}