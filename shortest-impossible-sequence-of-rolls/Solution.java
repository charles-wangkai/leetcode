import java.util.HashSet;
import java.util.Set;

class Solution {
  public int shortestSequence(int[] rolls, int k) {
    int result = 1;
    Set<Integer> seen = new HashSet<>();
    for (int roll : rolls) {
      seen.add(roll);
      if (seen.size() == k) {
        seen.clear();
        ++result;
      }
    }

    return result;
  }
}