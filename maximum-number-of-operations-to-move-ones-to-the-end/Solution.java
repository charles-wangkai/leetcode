class Solution {
  public int maxOperations(String s) {
    int result = 0;
    int oneCount = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '1') {
        ++oneCount;
      } else if (i == s.length() - 1 || s.charAt(i + 1) == '1') {
        result += oneCount;
      }
    }

    return result;
  }
}