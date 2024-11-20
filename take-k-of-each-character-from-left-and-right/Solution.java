import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  static final List<Character> LETTERS = List.of('a', 'b', 'c');

  public int takeCharacters(String s, int k) {
    if (LETTERS.stream().anyMatch(letter -> s.chars().filter(c -> c == letter).count() < k)) {
      return -1;
    }

    @SuppressWarnings("unused")
    Map<Character, Integer> letterToCount =
        LETTERS.stream().collect(Collectors.toMap(letter -> letter, letter -> 0));

    int leftIndex = -1;
    while (letterToCount.values().stream().anyMatch(count -> count < k)) {
      ++leftIndex;
      letterToCount.put(s.charAt(leftIndex), letterToCount.get(s.charAt(leftIndex)) + 1);
    }

    int result = leftIndex + 1;
    int rightIndex = s.length();
    while (leftIndex != -1) {
      letterToCount.put(s.charAt(leftIndex), letterToCount.get(s.charAt(leftIndex)) - 1);
      --leftIndex;

      while (letterToCount.values().stream().anyMatch(count -> count < k)) {
        --rightIndex;
        letterToCount.put(s.charAt(rightIndex), letterToCount.get(s.charAt(rightIndex)) + 1);
      }

      result = Math.min(result, (leftIndex + 1) + (s.length() - rightIndex));
    }

    return result;
  }
}
