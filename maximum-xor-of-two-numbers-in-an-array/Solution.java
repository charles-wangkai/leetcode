import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int findMaximumXOR(int[] nums) {
    int max = 0;
    int mask = 0;
    for (int i = 30; i >= 0; --i) {
      mask |= 1 << i;

      int mask_ = mask;
      Set<Integer> prefixes =
          Arrays.stream(nums).map(num -> num & mask_).boxed().collect(Collectors.toSet());

      int xor = max | (1 << i);
      if (prefixes.stream().anyMatch(prefix -> prefixes.contains(prefix ^ xor))) {
        max = xor;
      }
    }

    return max;
  }
}
