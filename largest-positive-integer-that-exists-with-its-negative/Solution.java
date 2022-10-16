import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int findMaxK(int[] nums) {
    Set<Integer> values = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    return Arrays.stream(nums).filter(x -> x > 0 && values.contains(-x)).max().orElse(-1);
  }
}
