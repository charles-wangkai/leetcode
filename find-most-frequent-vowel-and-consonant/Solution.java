import java.util.HashMap;
import java.util.Map;

class Solution {
  static final String VOWELS = "aeiou";

  public int maxFreqSum(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount.keySet().stream()
            .filter(this::isVowel)
            .mapToInt(letterToCount::get)
            .max()
            .orElse(0)
        + letterToCount.keySet().stream()
            .filter(letter -> !isVowel(letter))
            .mapToInt(letterToCount::get)
            .max()
            .orElse(0);
  }

  boolean isVowel(char letter) {
    return VOWELS.contains(String.valueOf(letter));
  }
}
