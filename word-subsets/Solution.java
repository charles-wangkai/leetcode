import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<String> wordSubsets(String[] words1, String[] words2) {
    Map<Character, Integer> letterToMaxCount = new HashMap<>();
    for (String word2 : words2) {
      Map<Character, Integer> letterToCount2 = buildLetterToCount(word2);
      for (char letter : letterToCount2.keySet()) {
        letterToMaxCount.put(
            letter, Math.max(letterToMaxCount.getOrDefault(letter, 0), letterToCount2.get(letter)));
      }
    }

    return Arrays.stream(words1).filter(word1 -> isUniversal(letterToMaxCount, word1)).toList();
  }

  Map<Character, Integer> buildLetterToCount(String word) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : word.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount;
  }

  boolean isUniversal(Map<Character, Integer> letterToMaxCount, String word) {
    Map<Character, Integer> letterToCount = buildLetterToCount(word);

    return letterToMaxCount.keySet().stream()
        .allMatch(letter -> letterToCount.getOrDefault(letter, 0) >= letterToMaxCount.get(letter));
  }
}
