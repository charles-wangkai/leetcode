class Solution {
  public boolean doesAliceWin(String s) {
    return s.chars().filter(c -> "aeiou".indexOf(c) != -1).count() != 0;
  }
}