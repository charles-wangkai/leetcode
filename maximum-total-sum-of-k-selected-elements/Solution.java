import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maxSum(int[] nums, int k, int mul) {
    int[] sorted =
        Arrays.stream(nums)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .limit(k)
            .toArray();

    return IntStream.range(0, sorted.length)
        .mapToLong(i -> sorted[i] * Math.max(1L, mul - i))
        .sum();
  }
}