class Solution {
  public int maxOperations(String s) {
    int result = 0;
    int oneCount = 0;
    char prev = 0;
    for (char c : s.toCharArray()) {
      if (c == '1') {
        ++oneCount;
      } else if (prev == '1') {
        result += oneCount;
      }

      prev = c;
    }

    return result;
  }
}