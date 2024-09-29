import java.util.HashSet;
import java.util.Set;

class Solution {
  static final String VOWELS = "aeiou";

  public int countOfSubstrings(String word, int k) {
    int result = 0;
    for (int i = 0; i < word.length(); ++i) {
      int consonantCount = 0;
      Set<Character> seenVowels = new HashSet<>();
      for (int j = i; j < word.length(); ++j) {
        char c = word.charAt(j);
        if (VOWELS.indexOf(c) == -1) {
          ++consonantCount;
        } else {
          seenVowels.add(c);
        }

        if (seenVowels.size() == VOWELS.length() && consonantCount == k) {
          ++result;
        }
      }
    }

    return result;
  }
}