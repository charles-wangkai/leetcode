import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class Solution {
  public int longestSquareStreak(int[] nums) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    return Arrays.stream(nums)
        .map(
            begin ->
                (int)
                    LongStream.iterate(begin, x -> x * x)
                        .takeWhile(x -> x <= Integer.MAX_VALUE && set.contains((int) x))
                        .count())
        .filter(x -> x >= 2)
        .max()
        .orElse(-1);
  }
}
