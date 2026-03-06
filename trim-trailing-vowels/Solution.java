class Solution {
  public String trimTrailingVowels(String s) {
    int length = s.length();
    while (length != 0 && isVowel(s.charAt(length - 1))) {
      --length;
    }

    return s.substring(0, length);
  }

  boolean isVowel(char c) {
    return "aeiou".indexOf(c) != -1;
  }
}