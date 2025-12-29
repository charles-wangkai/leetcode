import java.util.HashSet;
import java.util.Set;

class Solution {
  public int minAllOneMultiple(int k) {
    Set<Integer> seen = new HashSet<>();
    int remainder = 0;
    for (int result = 1; ; ++result) {
      remainder = (remainder * 10 + 1) % k;
      if (remainder == 0) {
        return result;
      }

      if (seen.contains(remainder)) {
        return -1;
      }
      seen.add(remainder);
    }
  }
}