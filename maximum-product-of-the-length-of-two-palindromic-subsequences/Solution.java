class Solution {
  static final int BASE = 3;

  public int maxProduct(String s) {
    int result = 0;
    int[] indices1 = new int[s.length()];
    int[] indices2 = new int[s.length()];
    for (int code = pow(s.length()) - 1; code >= 0; --code) {
      int length1 = 0;
      int length2 = 0;
      int rest = code;
      for (int i = 0; i < s.length(); ++i) {
        int group = rest % BASE;
        if (group == 1) {
          indices1[length1] = i;
          ++length1;
        } else if (group == 2) {
          indices2[length2] = i;
          ++length2;
        }

        rest /= BASE;
      }

      if (isPalindrome(s, indices1, length1) && isPalindrome(s, indices2, length2)) {
        result = Math.max(result, length1 * length2);
      }
    }

    return result;
  }

  boolean isPalindrome(String s, int[] indices, int length) {
    for (int i = 0, j = length - 1; i < j; ++i, --j) {
      if (s.charAt(indices[i]) != s.charAt(indices[j])) {
        return false;
      }
    }

    return true;
  }

  int pow(int exponent) {
    int result = 1;
    for (int i = 0; i < exponent; ++i) {
      result *= BASE;
    }

    return result;
  }
}
