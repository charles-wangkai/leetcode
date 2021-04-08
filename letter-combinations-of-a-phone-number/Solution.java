import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Solution {
  static final Map<Character, String> DIGIT_TO_LETTERS =
      Map.ofEntries(
          entry('2', "abc"),
          entry('3', "def"),
          entry('4', "ghi"),
          entry('5', "jkl"),
          entry('6', "mno"),
          entry('7', "pqrs"),
          entry('8', "tuv"),
          entry('9', "wxyz"));

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

    for (char letter : DIGIT_TO_LETTERS.get(digits.charAt(index)).toCharArray()) {
      current[index] = letter;
      search(combinations, digits, current, index + 1);
    }
  }
}
