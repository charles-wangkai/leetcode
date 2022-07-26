import java.util.HashSet;
import java.util.Set;

class Solution {
  public char repeatedCharacter(String s) {
    Set<Character> seen = new HashSet<>();
    for (int i = 0; ; ++i) {
      char c = s.charAt(i);
      if (seen.contains(c)) {
        return c;
      }

      seen.add(c);
    }
  }
}