import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int[] sortArrayByParity(int[] nums) {
    return Arrays.stream(nums)
        .boxed()
        .sorted(Comparator.comparing(x -> x % 2))
        .mapToInt(Integer::intValue)
        .toArray();
  }
}
