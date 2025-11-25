import java.util.HashSet;
import java.util.Set;

class Solution {
  public int smallestRepunitDivByK(int k) {
    Set<Integer> seen = new HashSet<>();
    int remainder = 0;
    for (int length = 1; ; ++length) {
      remainder = (remainder * 10 + 1) % k;
      if (remainder == 0) {
        return length;
      }
      if (seen.contains(remainder)) {
        return -1;
      }

      seen.add(remainder);
    }
  }
}
