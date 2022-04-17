import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int findClosestNumber(int[] nums) {
    return Arrays.stream(nums)
        .boxed()
        .min(
            Comparator.comparing((Integer num) -> Math.abs(num))
                .thenComparing(Comparator.reverseOrder()))
        .get();
  }
}