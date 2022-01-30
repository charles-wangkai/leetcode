import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int findFinalValue(int[] nums, int original) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    int result = original;
    while (set.contains(result)) {
      result *= 2;
    }

    return result;
  }
}