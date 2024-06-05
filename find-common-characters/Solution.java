import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public List<String> commonChars(String[] words) {
    Map<Character, Integer> merged = buildLetterToCount(words[0]);
    for (int i = 1; i < words.length; ++i) {
      Map<Character, Integer> letterToCount = buildLetterToCount(words[i]);

      for (char letter : merged.keySet()) {
        merged.put(letter, Math.min(merged.get(letter), letterToCount.getOrDefault(letter, 0)));
      }
    }

    return merged.keySet().stream()
        .flatMap(
            letter ->
                IntStream.range(0, merged.get(letter)).mapToObj(i -> letter).map(String::valueOf))
        .toList();
  }

  Map<Character, Integer> buildLetterToCount(String word) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : word.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount;
  }
}
