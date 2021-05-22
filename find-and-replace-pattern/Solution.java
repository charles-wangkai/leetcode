import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public List<String> findAndReplacePattern(String[] words, String pattern) {
    String patternKey = computeKey(pattern);

    return Arrays.stream(words)
        .filter(word -> computeKey(word).equals(patternKey))
        .collect(Collectors.toList());
  }

  String computeKey(String s) {
    StringBuilder key = new StringBuilder();
    Map<Character, Character> letterToReplacement = new HashMap<>();
    for (char letter : s.toCharArray()) {
      if (!letterToReplacement.containsKey(letter)) {
        letterToReplacement.put(letter, (char) ('a' + letterToReplacement.size()));
      }

      key.append(letterToReplacement.get(letter));
    }

    return key.toString();
  }
}
