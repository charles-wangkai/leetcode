class Solution {
  public int minOperations(String s1, String s2) {
    if (s1.equals("1") && s2.equals("0")) {
      return -1;
    }

    int result = 0;
    int index = 0;
    while (index != s1.length()) {
      if (s1.charAt(index) == s2.charAt(index)) {
        ++index;
      } else if (index != s1.length() - 1
          && s1.charAt(index) == '1'
          && s1.charAt(index + 1) == '1'
          && s2.charAt(index) == '0'
          && s2.charAt(index + 1) == '0') {
        ++result;
        index += 2;
      } else if (s1.charAt(index) == '0') {
        ++result;
        ++index;
      } else {
        result += 2;
        ++index;
      }
    }

    return result;
  }
}