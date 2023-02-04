import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int maxCount(int[] banned, int n, int maxSum) {
    int result = 0;
    Set<Integer> bannedSet = Arrays.stream(banned).boxed().collect(Collectors.toSet());
    for (int i = 1; i <= n; ++i) {
      if (!bannedSet.contains(i) && i <= maxSum) {
        maxSum -= i;
        ++result;
      }
    }

    return result;
  }
}
