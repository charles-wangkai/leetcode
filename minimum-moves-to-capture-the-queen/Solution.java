class Solution {
  public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
    return ((a == e && (c != a || (b - d) * (f - d) > 0))
            || (b == f && (d != b || (a - c) * (e - c) > 0))
            || (c + d == e + f && (a + b != c + d || (c - a) * (e - a) > 0))
            || (c - d == e - f && (a - b != c - d || (c - a) * (e - a) > 0)))
        ? 1
        : 2;
  }
}