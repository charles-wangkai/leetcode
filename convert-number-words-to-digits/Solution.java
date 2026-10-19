import static java.util.Map.entry;

import java.util.Map;

class Solution {
  static final Map<String, Integer> WORD_TO_DIGIT =
      Map.ofEntries(
          entry("zero", 0),
          entry("one", 1),
          entry("two", 2),
          entry("three", 3),
          entry("four", 4),
          entry("five", 5),
          entry("six", 6),
          entry("seven", 7),
          entry("eight", 8),
          entry("nine", 9));

  public String convertNumber(String s) {
    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index != s.length()) {
      boolean found = false;
      for (String word : WORD_TO_DIGIT.keySet()) {
        if (s.startsWith(word, index)) {
          result.append(WORD_TO_DIGIT.get(word));
          index += word.length();
          found = true;

          break;
        }
      }

      if (!found) {
        ++index;
      }
    }

    return result.toString();
  }
}