import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String decodeString(String s) {
    int level = 0;
    int startIndex = -1;
    int k = 0;

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      if (ch == '[') {
        if (level == 0) {
          startIndex = i;
        }

        ++level;
      } else if (ch == ']') {
        --level;

        if (level == 0) {
          result.append(repeat(decodeString(s.substring(startIndex + 1, i)), k));
          k = 0;
        }
      } else if (level == 0) {
        if (Character.isDigit(ch)) {
          k = k * 10 + (ch - '0');
        } else {
          result.append(ch);
        }
      }
    }

    return result.toString();
  }

  String repeat(String str, int k) {
    return IntStream.range(0, k).mapToObj(i -> str).collect(Collectors.joining());
  }
}
