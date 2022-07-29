import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<String> findAndReplacePattern(String[] words, String pattern) {
    String patternKey = computeKey(pattern);

    return Arrays.stream(words).filter(word -> computeKey(word).equals(patternKey)).toList();
  }

  String computeKey(String s) {
    StringBuilder key = new StringBuilder();
    Map<Character, Character> letterToReplacement = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToReplacement.putIfAbsent(letter, (char) ('a' + letterToReplacement.size()));
      key.append(letterToReplacement.get(letter));
    }

    return key.toString();
  }
}
