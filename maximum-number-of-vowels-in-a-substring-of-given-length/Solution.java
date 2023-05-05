import java.util.stream.IntStream;

class Solution {
  public int maxVowels(String s, int k) {
    int result = -1;
    int vowelCount = (int) IntStream.range(0, k - 1).filter(i -> isVowel(s.charAt(i))).count();
    for (int i = k - 1; i < s.length(); ++i) {
      vowelCount += isVowel(s.charAt(i)) ? 1 : 0;
      result = Math.max(result, vowelCount);

      vowelCount -= isVowel(s.charAt(i - k + 1)) ? 1 : 0;
    }

    return result;
  }

  boolean isVowel(char c) {
    return "aeiou".indexOf(c) != -1;
  }
}
