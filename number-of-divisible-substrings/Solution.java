import static java.util.Map.entry;

import java.util.Map;

class Solution {
  static final Map<Character, Integer> LETTER_TO_DIGIT =
      Map.ofEntries(
          entry('a', 1),
          entry('b', 1),
          entry('c', 2),
          entry('d', 2),
          entry('e', 2),
          entry('f', 3),
          entry('g', 3),
          entry('h', 3),
          entry('i', 4),
          entry('j', 4),
          entry('k', 4),
          entry('l', 5),
          entry('m', 5),
          entry('n', 5),
          entry('o', 6),
          entry('p', 6),
          entry('q', 6),
          entry('r', 7),
          entry('s', 7),
          entry('t', 7),
          entry('u', 8),
          entry('v', 8),
          entry('w', 8),
          entry('x', 9),
          entry('y', 9),
          entry('z', 9));

  public int countDivisibleSubstrings(String word) {
    int result = 0;
    for (int i = 0; i < word.length(); ++i) {
      int sum = 0;
      for (int j = i; j < word.length(); ++j) {
        sum += LETTER_TO_DIGIT.get(word.charAt(j));
        if (sum % (j - i + 1) == 0) {
          ++result;
        }
      }
    }

    return result;
  }
}