import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int minImpossibleOR(int[] nums) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    for (int i = 1; ; i <<= 1) {
      if (!set.contains(i)) {
        return i;
      }
    }
  }
}
