class Solution {
  public boolean doesAliceWin(String s) {
    return s.chars().anyMatch(c -> "aeiou".contains(String.valueOf((char) c)));
  }
}