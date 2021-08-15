import java.util.HashMap;
import java.util.Map;

class Solution {
  public String minWindow(String s, String t) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : t.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    String result = null;
    int back = 0;
    for (int front = 0; front < s.length(); ++front) {
      char frontLetter = s.charAt(front);
      if (letterToCount.containsKey(frontLetter)) {
        letterToCount.put(frontLetter, letterToCount.get(frontLetter) - 1);
        while (letterToCount.values().stream().allMatch(count -> count <= 0)) {
          if (result == null || front - back + 1 < result.length()) {
            result = s.substring(back, front + 1);
          }

          char backLetter = s.charAt(back);
          if (letterToCount.containsKey(backLetter)) {
            letterToCount.put(backLetter, letterToCount.get(backLetter) + 1);
          }
          ++back;
        }
      }
    }

    return (result == null) ? "" : result;
  }
}
