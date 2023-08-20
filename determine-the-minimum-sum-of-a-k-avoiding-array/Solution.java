import java.util.HashSet;
import java.util.Set;

class Solution {
  public int minimumSum(int n, int k) {
    int result = 0;
    Set<Integer> seen = new HashSet<>();
    int value = 1;
    for (int i = 0; i < n; ++i) {
      while (seen.contains(k - value)) {
        ++value;
      }

      result += value;
      seen.add(value);
      ++value;
    }

    return result;
  }
}
