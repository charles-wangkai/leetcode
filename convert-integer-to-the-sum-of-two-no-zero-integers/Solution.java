class Solution {
  public int[] getNoZeroIntegers(int n) {
    for (int a = 1; ; ++a) {
      int b = n - a;
      if (!hasZero(a) && !hasZero(b)) {
        return new int[] {a, b};
      }
    }
  }

  boolean hasZero(int x) {
    return String.valueOf(x).contains("0");
  }
}
