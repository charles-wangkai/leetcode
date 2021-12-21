class Solution {
  public boolean isPowerOfTwo(int n) {
    return n >= 1 && (n & (n - 1)) == 0;
  }
}
