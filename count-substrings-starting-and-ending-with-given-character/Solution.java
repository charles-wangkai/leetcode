class Solution {
  public long countSubstrings(String s, char c) {
    int count = (int) s.chars().filter(x -> x == c).count();

    return count * (count + 1L) / 2;
  }
}