import java.util.HashMap;
import java.util.Map;

class Solution {
  public String mergeCharacters(String s, int k) {
    StringBuilder result = new StringBuilder();
    Map<Character, Integer> letterToLastIndex = new HashMap<>();
    for (char letter : s.toCharArray()) {
      if (!letterToLastIndex.containsKey(letter)
          || result.length() - letterToLastIndex.get(letter) > k) {
        letterToLastIndex.put(letter, result.length());
        result.append(letter);
      }
    }

    return result.toString();
  }
}