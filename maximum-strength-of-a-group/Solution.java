import java.util.stream.IntStream;

class Solution {
  public long maxStrength(int[] nums) {
    return IntStream.range(1, 1 << nums.length)
        .mapToLong(
            mask ->
                IntStream.range(0, nums.length)
                    .filter(i -> ((mask >> i) & 1) == 1)
                    .map(i -> nums[i])
                    .asLongStream()
                    .reduce((acc, x) -> acc * x)
                    .getAsLong())
        .max()
        .getAsLong();
  }
}
