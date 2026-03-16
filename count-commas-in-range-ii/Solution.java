class Solution {
  public long countCommas(long n) {
    long result = 0;
    for (long tenPower = 1; tenPower <= n; tenPower *= 10) {
      result +=
          (String.valueOf(tenPower).length() - 1)
              / 3
              * (Math.min(tenPower * 10 - 1, n) - tenPower + 1);
    }

    return result;
  }
}