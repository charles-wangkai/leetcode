import java.util.stream.IntStream;

class Solution {
  static final String VOWELS = "aeiou";

  public long countVowels(String word) {
    return IntStream.range(0, word.length())
        .filter(i -> VOWELS.indexOf(word.charAt(i)) >= 0)
        .mapToLong(i -> (i + 1L) * (word.length() - i))
        .sum();
  }
}
