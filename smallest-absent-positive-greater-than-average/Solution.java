import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int smallestAbsent(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    Set<Integer> values = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    for (int result = 1; ; ++result) {
      if (result * nums.length > sum && !values.contains(result)) {
        return result;
      }
    }
  }
}