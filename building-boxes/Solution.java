class Solution {
  public int minimumBoxes(int n) {
    int size = 0;
    int total = 0;
    int bottom = 0;
    while (total + bottom + size + 1 <= n) {
      ++size;
      bottom += size;
      total += bottom;
    }

    return bottom + computeBottomForExtra(n - total);
  }

  int computeBottomForExtra(int extra) {
    int bottom = 0;
    while (bottom * (bottom + 1) / 2 < extra) {
      ++bottom;
    }

    return bottom;
  }
}
