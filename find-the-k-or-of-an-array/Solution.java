import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 31;

  public int findKOr(int[] nums, int k) {
    return IntStream.range(0, BIT_NUM)
        .map(
            i ->
                (Arrays.stream(nums).filter(num -> ((num >> i) & 1) == 1).count() >= k)
                    ? (1 << i)
                    : 0)
        .sum();
  }
}
