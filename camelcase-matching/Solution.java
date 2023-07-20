import java.util.Arrays;
import java.util.List;

public class Solution {
  public List<Boolean> camelMatch(String[] queries, String pattern) {
    return Arrays.stream(queries).map(query -> isCamelMatch(query, pattern)).toList();
  }

  boolean isCamelMatch(String query, String pattern) {
    int patternIndex = 0;
    for (char target : query.toCharArray()) {
      if (patternIndex == pattern.length()) {
        if (!Character.isLowerCase(target)) {
          return false;
        }
      } else if (pattern.charAt(patternIndex) == target) {
        patternIndex++;
      } else if (!Character.isLowerCase(target)) {
        return false;
      }
    }
    return patternIndex == pattern.length();
  }
}
