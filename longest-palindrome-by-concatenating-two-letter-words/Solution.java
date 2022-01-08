import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestPalindrome(String[] words) {
    Map<String, Integer> wordToCount = new HashMap<>();
    for (String word : words) {
      wordToCount.put(word, wordToCount.getOrDefault(word, 0) + 1);
    }

    int result = 0;
    for (String word : words) {
      String reversed = new StringBuilder(word).reverse().toString();
      if ((word.equals(reversed) && wordToCount.get(word) >= 2)
          || (!word.equals(reversed)
              && wordToCount.get(word) >= 1
              && wordToCount.getOrDefault(reversed, 0) >= 1)) {
        result += 4;

        wordToCount.put(word, wordToCount.get(word) - 1);
        wordToCount.put(reversed, wordToCount.get(reversed) - 1);
      }
    }
    if (wordToCount.keySet().stream()
        .anyMatch(word -> wordToCount.get(word) >= 1 && word.charAt(0) == word.charAt(1))) {
      result += 2;
    }

    return result;
  }
}