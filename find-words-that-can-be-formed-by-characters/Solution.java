import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int countCharacters(String[] words, String chars) {
    Map<Character, Integer> letterToCount = buildLetterToCount(chars);

    return Arrays.stream(words)
        .filter(word -> isCovered(buildLetterToCount(word), letterToCount))
        .mapToInt(String::length)
        .sum();
  }

  Map<Character, Integer> buildLetterToCount(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount;
  }

  boolean isCovered(
      Map<Character, Integer> letterToCount1, Map<Character, Integer> letterToCount2) {
    return letterToCount1.keySet().stream()
        .allMatch(letter -> letterToCount1.get(letter) <= letterToCount2.getOrDefault(letter, 0));
  }
}
