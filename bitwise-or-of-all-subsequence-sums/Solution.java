import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 47;

  public long subsequenceSumOr(int[] nums) {
    return IntStream.range(0, BIT_NUM)
        .filter(
            i ->
                Arrays.stream(nums).map(x -> (int) (x % (1L << (i + 1)))).asLongStream().sum()
                    >= 1L << i)
        .mapToLong(i -> 1L << i)
        .sum();
  }
}
