import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumLength(String s) {
    int result = -1;
    int lower = 1;
    int upper = s.length();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(s, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(String s, int length) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    char current = 0;
    int l = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != s.length() && s.charAt(i) == current) {
        ++l;
      } else {
        if (l >= length) {
          letterToCount.put(current, letterToCount.getOrDefault(current, 0) + (l - length + 1));
        }

        if (i != s.length()) {
          current = s.charAt(i);
          l = 1;
        }
      }
    }

    return letterToCount.values().stream().anyMatch(count -> count >= 3);
  }
}