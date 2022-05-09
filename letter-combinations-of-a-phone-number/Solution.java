import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Solution {
  static final Map<Character, char[]> DIGIT_TO_LETTERS =
      Map.ofEntries(
          entry('2', new char[] {'a', 'b', 'c'}),
          entry('3', new char[] {'d', 'e', 'f'}),
          entry('4', new char[] {'g', 'h', 'i'}),
          entry('5', new char[] {'j', 'k', 'l'}),
          entry('6', new char[] {'m', 'n', 'o'}),
          entry('7', new char[] {'p', 'q', 'r', 's'}),
          entry('8', new char[] {'t', 'u', 'v'}),
          entry('9', new char[] {'w', 'x', 'y', 'z'}));

  public List<String> letterCombinations(String digits) {
    if (digits.isEmpty()) {
      return List.of();
    }

    List<String> combinations = new ArrayList<>();
    search(combinations, digits, new char[digits.length()], 0);

    return combinations;
  }

  void search(List<String> combinations, String digits, char[] current, int index) {
    if (index == current.length) {
      combinations.add(new String(current));

      return;
    }

    for (char letter : DIGIT_TO_LETTERS.get(digits.charAt(index))) {
      current[index] = letter;
      search(combinations, digits, current, index + 1);
    }
  }
}
