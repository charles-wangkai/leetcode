import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int maxDivScore(int[] nums, int[] divisors) {
    return Arrays.stream(divisors)
        .boxed()
        .min(
            Comparator.comparing(
                    (Integer divisor) ->
                        Arrays.stream(nums).filter(num -> num % divisor == 0).count())
                .reversed()
                .thenComparing(divisor -> divisor))
        .get();
  }
}
