import java.util.stream.IntStream;

class Solution {
  public int minMergedInteger(int[] nums) {
    return IntStream.range(0, nums.length / 2)
        .map(i -> Integer.parseInt("%d%d".formatted(nums[i * 2], nums[i * 2 + 1])))
        .min()
        .getAsInt();
  }
}