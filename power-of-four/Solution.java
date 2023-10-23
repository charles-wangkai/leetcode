class Solution {
  public boolean isPowerOfFour(int n) {
    return n > 0 && (1 << 30) % n == 0 && (n & 0x55555555) != 0;
  }
}
