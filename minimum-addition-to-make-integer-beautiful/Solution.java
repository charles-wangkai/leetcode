class Solution {
  public long makeIntegerBeautiful(long n, int target) {
    if (computeDigitSum(n) <= target) {
      return 0;
    }

    String nStr = String.valueOf(n);

    int digitSum = 0;
    int index = 0;
    while (digitSum + (nStr.charAt(index) - '0') <= target) {
      digitSum += nStr.charAt(index) - '0';
      ++index;
    }
    if (digitSum == target) {
      while (index != 0 && nStr.charAt(index - 1) == '0') {
        --index;
      }
    }

    long placeValue = Long.parseLong(String.format("1%s", "0".repeat(nStr.length() - index)));
    for (long i = n / placeValue * placeValue + placeValue; ; i += placeValue) {
      if (computeDigitSum(i) <= target) {
        return i - n;
      }
    }
  }

  static int computeDigitSum(long x) {
    return String.valueOf(x).chars().map(c -> c - '0').sum();
  }
}
