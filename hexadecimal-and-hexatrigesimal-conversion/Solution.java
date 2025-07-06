class Solution {
  public String concatHex36(int n) {
    return toRepresentation(n * n, 16) + toRepresentation(n * n * n, 36);
  }

  static String toRepresentation(int x, int base) {
    StringBuilder sb = new StringBuilder();
    while (x != 0) {
      int digit = x % base;
      sb.append((char) ((digit < 10) ? (digit + '0') : (digit - 10 + 'A')));

      x /= base;
    }

    return sb.reverse().toString();
  }
}