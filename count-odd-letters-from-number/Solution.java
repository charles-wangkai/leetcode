import java.util.HashMap;
import java.util.Map;

class Solution {
  static final String[] words = {
    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
  };

  public int countOddLetters(int n) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    while (n != 0) {
      int digit = n % 10;
      for (char letter : words[digit].toCharArray()) {
        letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
      }

      n /= 10;
    }

    return (int) letterToCount.values().stream().filter(count -> count % 2 == 1).count();
  }
}