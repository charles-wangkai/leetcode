class Solution {
  public long maxNumber(long n) {
    return Long.highestOneBit(n) - 1;
  }
}