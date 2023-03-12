import java.util.stream.IntStream;

class Solution {
  public int vowelStrings(String[] words, int left, int right) {
    return (int)
        IntStream.rangeClosed(left, right)
            .filter(
                i -> isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length() - 1)))
            .count();
  }

  boolean isVowel(char c) {
    return "aeiou".indexOf(c) >= 0;
  }
}
