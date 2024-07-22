class Solution {
  public int minChanges(int n, int k) {
    return ((n & k) == k) ? Integer.bitCount(n ^ k) : -1;
  }
}