import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public String longestNiceSubstring(String s) {
    for (int length = s.length(); length >= 1; --length) {
      for (int beginIndex = 0; beginIndex + length <= s.length(); ++beginIndex) {
        String substring = s.substring(beginIndex, beginIndex + length);
        if (isNice(substring)) {
          return substring;
        }
      }
    }

    return "";
  }

  static boolean isNice(String str) {
    Set<Character> letters = str.chars().mapToObj(ch -> (char) ch).collect(Collectors.toSet());

    return str.chars()
        .allMatch(
            ch ->
                Character.isLowerCase(ch)
                    ? letters.contains(Character.toUpperCase((char) ch))
                    : letters.contains(Character.toLowerCase((char) ch)));
  }
}
