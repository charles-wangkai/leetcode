import java.util.stream.IntStream;

class Solution {
  public long minCost(int[] nums, int x) {
    int[][] rangeMins = new int[nums.length][nums.length];
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      for (int i = 0; i < nums.length; ++i) {
        rangeMins[beginIndex][i] =
            Math.min(
                (i == 0) ? Integer.MAX_VALUE : rangeMins[beginIndex][i - 1],
                nums[(beginIndex + i) % nums.length]);
      }
    }

    return IntStream.range(0, nums.length)
        .mapToLong(
            i ->
                IntStream.range(0, nums.length)
                        .map(beginIndex -> rangeMins[beginIndex][i])
                        .asLongStream()
                        .sum()
                    + (long) i * x)
        .min()
        .getAsLong();
  }
}
