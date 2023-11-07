import java.util.HashSet;
import java.util.Set;

class Solution {
  public int numberOfSpecialSubstrings(String s) {
    int result = 0;
    Set<Character> seen = new HashSet<>();
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      while (endIndex != s.length() - 1 && !seen.contains(s.charAt(endIndex + 1))) {
        ++endIndex;
        seen.add(s.charAt(endIndex));
      }

      result += endIndex - beginIndex + 1;

      seen.remove(s.charAt(beginIndex));
    }

    return result;
  }
}
