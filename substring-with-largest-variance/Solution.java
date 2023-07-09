class Solution {
  public int largestVariance(String s) {
    int result = 0;
    for (char letter1 = 'a'; letter1 <= 'z'; ++letter1) {
      for (char letter2 = 'a'; letter2 <= 'z'; ++letter2) {
        if (letter2 != letter1) {
          result = Math.max(result, computeMaxVariance(s, letter1, letter2));
        }
      }
    }

    return result;
  }

  int computeMaxVariance(String s, char letter1, char letter2) {
    int result = 0;
    int sum = 0;
    boolean firstNegative = false;
    boolean hasNegative = false;
    for (char c : s.toCharArray()) {
      if (c == letter1) {
        ++sum;
      } else if (c == letter2) {
        if (firstNegative) {
          firstNegative = false;
        } else {
          --sum;
        }

        hasNegative = true;
      }

      if (hasNegative) {
        result = Math.max(result, sum);
      }

      sum = Math.max(-1, sum);
      if (sum == -1) {
        firstNegative = true;
      }
    }

    return result;
  }
}
