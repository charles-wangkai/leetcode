import java.util.HashSet;
import java.util.Set;

class Solution {
  public long minimumPossibleSum(int n, int target) {
    long result = 0;
    int value = 0;
    Set<Integer> seen = new HashSet<>();
    for (int i = 0; i < n; ++i) {
      ++value;
      while (seen.contains(target - value)) {
        ++value;
      }

      result += value;
      seen.add(value);
    }

    return result;
  }
}
