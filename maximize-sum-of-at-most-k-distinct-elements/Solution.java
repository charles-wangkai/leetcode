import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int[] maxKDistinct(int[] nums, int k) {
    return Arrays.stream(nums)
        .distinct()
        .boxed()
        .sorted(Comparator.reverseOrder())
        .mapToInt(Integer::intValue)
        .limit(k)
        .toArray();
  }
}