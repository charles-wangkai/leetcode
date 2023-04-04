import java.util.HashSet;
import java.util.Set;

class Solution {
  public int partitionString(String s) {
    int result = 1;
    Set<Character> seen = new HashSet<>();
    for (char c : s.toCharArray()) {
      if (seen.contains(c)) {
        ++result;
        seen.clear();
      }
      seen.add(c);
    }

    return result;
  }
}
