import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int findClosestNumber(int[] nums) {
    return Arrays.stream(nums)
        .boxed()
        .min(
            Comparator.<Integer, Integer>comparing(Math::abs)
                .thenComparing(Comparator.reverseOrder()))
        .get();
  }
}
