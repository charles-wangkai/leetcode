import java.util.HashSet;
import java.util.Set;

class Solution {
  public int residuePrefixes(String s) {
    int result = 0;
    Set<Character> seen = new HashSet<>();
    for (int i = 0; i < s.length(); ++i) {
      seen.add(s.charAt(i));

      if (seen.size() == (i + 1) % 3) {
        ++result;
      }
    }

    return result;
  }
}