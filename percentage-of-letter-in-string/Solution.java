class Solution {
  public int percentageLetter(String s, char letter) {
    return (int) s.chars().filter(c -> c == letter).count() * 100 / s.length();
  }
}