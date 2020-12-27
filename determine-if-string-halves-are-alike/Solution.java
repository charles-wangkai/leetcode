class Solution {
  public boolean halvesAreAlike(String s) {
    return computeVowelNum(s.substring(0, s.length() / 2))
        == computeVowelNum(s.substring(s.length() / 2));
  }

  int computeVowelNum(String str) {
    return str.toLowerCase().replaceAll("[^aeiou]", "").length();
  }
}
