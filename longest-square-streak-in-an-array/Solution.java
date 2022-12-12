import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int longestSquareStreak(int[] nums) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    return Arrays.stream(nums)
        .map(begin -> (int) IntStream.iterate(begin, x -> x * x).takeWhile(set::contains).count())
        .filter(x -> x >= 2)
        .max()
        .orElse(-1);
  }
}
