class Solution {
  public boolean halvesAreAlike(String s) {
    return computeVowelNum(s.substring(0, s.length() / 2))
        == computeVowelNum(s.substring(s.length() / 2));
  }

  int computeVowelNum(String str) {
    return (int) str.toLowerCase().chars().filter(c -> "aeiou".indexOf(c) != -1).count();
  }
}
