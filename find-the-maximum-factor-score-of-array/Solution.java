import java.util.stream.IntStream;

class Solution {
  public long maxScore(int[] nums) {
    return IntStream.range(-1, nums.length).mapToLong(i -> computeScore(nums, i)).max().getAsLong();
  }

  long computeScore(int[] nums, int excludedIndex) {
    if (excludedIndex != -1 && nums.length == 1) {
      return 0;
    }

    return IntStream.range(0, nums.length)
            .filter(i -> i != excludedIndex)
            .map(i -> nums[i])
            .reduce((acc, x) -> (int) gcd(acc, x))
            .getAsInt()
        * IntStream.range(0, nums.length)
            .filter(i -> i != excludedIndex)
            .map(i -> nums[i])
            .asLongStream()
            .reduce(this::lcm)
            .getAsLong();
  }

  long gcd(long x, long y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  long lcm(long x, long y) {
    return x / gcd(x, y) * y;
  }
}