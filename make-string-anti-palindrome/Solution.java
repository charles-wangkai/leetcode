// https://leetcode.com/problems/make-string-anti-palindrome/solutions/4904771/python-3-greed-swap-with-detailed-explanation/

import java.util.Arrays;

class Solution {
  public String makeAntiPalindrome(String s) {
    char[] result = s.toCharArray();
    Arrays.sort(result);

    int leftIndex = result.length / 2;
    int rightIndex = leftIndex;
    while (rightIndex != result.length && result[rightIndex] == result[leftIndex]) {
      ++rightIndex;
    }

    while (result[leftIndex] == result[result.length - 1 - leftIndex]) {
      if (rightIndex == result.length) {
        return "-1";
      }

      char temp = result[leftIndex];
      result[leftIndex] = result[rightIndex];
      result[rightIndex] = temp;

      ++leftIndex;
      ++rightIndex;
    }

    return new String(result);
  }
}