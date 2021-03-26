import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public List<String> wordSubsets(String[] A, String[] B) {
    Map<Character, Integer> letterToMinCount = new HashMap<>();
    for (String wordB : B) {
      Map<Character, Integer> letterToCountB = buildLetterToCount(wordB);
      for (char letter : letterToCountB.keySet()) {
        letterToMinCount.put(
            letter, Math.max(letterToMinCount.getOrDefault(letter, 0), letterToCountB.get(letter)));
      }
    }

    return Arrays.stream(A)
        .filter(wordA -> isUniversal(letterToMinCount, wordA))
        .collect(Collectors.toList());
  }

  Map<Character, Integer> buildLetterToCount(String word) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : word.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount;
  }

  boolean isUniversal(Map<Character, Integer> letterToMinCount, String word) {
    Map<Character, Integer> letterToCount = buildLetterToCount(word);

    return letterToMinCount.entrySet().stream()
        .allMatch(entry -> letterToCount.getOrDefault(entry.getKey(), 0) >= entry.getValue());
  }
}
