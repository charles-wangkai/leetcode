import java.util.stream.IntStream;

class Solution {
  public int subsetXORSum(int[] nums) {
    return IntStream.range(0, 1 << nums.length)
        .map(
            mask ->
                IntStream.range(0, nums.length)
                    .filter(i -> ((mask >> i) & 1) == 1)
                    .map(i -> nums[i])
                    .reduce((acc, x) -> acc ^ x)
                    .orElse(0))
        .sum();
  }
}
