class Solution {
  public String reversePrefix(String s, int k) {
    return "%s%s".formatted(new StringBuilder(s.substring(0, k)).reverse(), s.substring(k));
  }
}