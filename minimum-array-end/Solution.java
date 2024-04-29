class Solution {
  public long minEnd(int n, int x) {
    long result = x;
    int bitPos = 0;
    int rest = n - 1;
    while (rest != 0) {
      while (((result >> bitPos) & 1) == 1) {
        ++bitPos;
      }

      result += (rest & 1L) << bitPos;

      ++bitPos;
      rest >>= 1;
    }

    return result;
  }
}