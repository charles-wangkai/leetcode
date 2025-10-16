import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int[] sortByAbsoluteValue(int[] nums) {
    return Arrays.stream(nums)
        .boxed()
        .sorted(Comparator.comparing(Math::abs))
        .mapToInt(Integer::intValue)
        .toArray();
  }
}