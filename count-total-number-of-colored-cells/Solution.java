class Solution {
  public long coloredCells(int n) {
    long result = 1;
    for (int i = 0; i < n; ++i) {
      result += i * 4;
    }

    return result;
  }
}
