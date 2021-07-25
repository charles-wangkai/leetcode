class Solution {
  public String maximumNumber(String num, int[] change) {
    char[] digits = num.toCharArray();

    int beginIndex = 0;
    while (beginIndex != num.length()
        && digits[beginIndex] - '0' >= change[digits[beginIndex] - '0']) {
      ++beginIndex;
    }

    if (beginIndex == num.length()) {
      return num;
    }

    int endIndex = beginIndex;
    while (endIndex + 1 != num.length()
        && digits[endIndex + 1] - '0' <= change[digits[endIndex + 1] - '0']) {
      ++endIndex;
    }

    for (int i = beginIndex; i <= endIndex; ++i) {
      digits[i] = (char) ('0' + change[digits[i] - '0']);
    }

    return new String(digits);
  }
}
