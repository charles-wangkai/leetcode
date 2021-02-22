import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestPalindrome(String word1, String word2) {
    String s = word1 + word2;
    int[][] palindromeLengths = new int[s.length()][s.length()];
    for (int length = 1; length <= s.length(); ++length) {
      for (int beginIndex = 0; beginIndex + length <= s.length(); ++beginIndex) {
        int endIndex = beginIndex + length - 1;
        if (length == 1) {
          palindromeLengths[beginIndex][endIndex] = 1;
        } else if (s.charAt(beginIndex) == s.charAt(endIndex)) {
          palindromeLengths[beginIndex][endIndex] =
              2 + ((length == 2) ? 0 : palindromeLengths[beginIndex + 1][endIndex - 1]);
        } else {
          palindromeLengths[beginIndex][endIndex] =
              Math.max(
                  palindromeLengths[beginIndex + 1][endIndex],
                  palindromeLengths[beginIndex][endIndex - 1]);
        }
      }
    }

    Map<Character, Integer> leftLetterToIndex = new HashMap<>();
    for (int i = 0; i < word1.length(); ++i) {
      leftLetterToIndex.putIfAbsent(word1.charAt(i), i);
    }

    Map<Character, Integer> rightLetterToIndex = new HashMap<>();
    for (int i = word2.length() - 1; i >= 0; --i) {
      rightLetterToIndex.putIfAbsent(word2.charAt(i), word1.length() + i);
    }

    int result = 0;
    for (char letter : leftLetterToIndex.keySet()) {
      int leftIndex = leftLetterToIndex.get(letter);
      if (rightLetterToIndex.containsKey(letter)) {
        int rightIndex = rightLetterToIndex.get(letter);

        result =
            Math.max(
                result,
                2
                    + ((leftIndex + 1 == rightIndex)
                        ? 0
                        : palindromeLengths[leftIndex + 1][rightIndex - 1]));
      }
    }

    return result;
  }
}
