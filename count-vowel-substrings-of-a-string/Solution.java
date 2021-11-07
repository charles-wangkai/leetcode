class Solution {
  static final String VOWELS = "aeiou";

  public int countVowelSubstrings(String word) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < word.length(); ++beginIndex) {
      for (int endIndex = beginIndex; endIndex < word.length(); ++endIndex) {
        String s = word.substring(beginIndex, endIndex + 1);
        if (s.chars().allMatch(ch -> VOWELS.indexOf(ch) >= 0)
            && s.chars().distinct().count() == VOWELS.length()) {
          ++result;
        }
      }
    }

    return result;
  }
}
