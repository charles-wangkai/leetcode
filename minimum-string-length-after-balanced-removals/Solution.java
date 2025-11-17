class Solution {
  public int minLengthAfterRemovals(String s) {
    return Math.abs(s.length() - 2 * (int) s.chars().filter(c -> c == 'a').count());
  }
}