import java.util.HashMap;
import java.util.Map;

class Solution {
  public String smallestSubsequence(String s) {
    if (s.isEmpty()) {
      return "";
    }

    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    int selectedIndex = 0;
    for (int i = 0; ; ++i) {
      char letter = s.charAt(i);
      if (letter < s.charAt(selectedIndex)) {
        selectedIndex = i;
      }

      if (letterToCount.get(letter) == 1) {
        break;
      }

      letterToCount.put(letter, letterToCount.get(letter) - 1);
    }

    return "%c%s"
        .formatted(
            s.charAt(selectedIndex),
            smallestSubsequence(
                s.substring(selectedIndex + 1)
                    .replace(String.valueOf(s.charAt(selectedIndex)), "")));
  }
}
