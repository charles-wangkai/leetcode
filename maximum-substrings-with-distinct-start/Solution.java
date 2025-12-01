class Solution {
  public int maxDistinct(String s) {
    return (int) s.chars().distinct().count();
  }
}