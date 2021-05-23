class Solution {
  public boolean checkZeroOnes(String s) {
    return computeMaxLength(s, '1') > computeMaxLength(s, '0');
  }

  int computeMaxLength(String s, char target) {
    int result = 0;
    int count = 0;
    for (char ch : s.toCharArray()) {
      if (ch == target) {
        ++count;
        result = Math.max(result, count);
      } else {
        count = 0;
      }
    }

    return result;
  }
}
