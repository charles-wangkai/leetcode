class Solution {
  public String countAndSay(int n) {
    String result = "1";
    for (int i = 0; i < n - 1; ++i) {
      result = buildNext(result);
    }

    return result;
  }

  String buildNext(String s) {
    StringBuilder result = new StringBuilder();
    char digit = s.charAt(0);
    int count = 1;
    for (int i = 1; i <= s.length(); ++i) {
      if (i != s.length() && s.charAt(i) == digit) {
        ++count;
      } else {
        result.append(count).append(digit);

        if (i != s.length()) {
          digit = s.charAt(i);
          count = 1;
        }
      }
    }

    return result.toString();
  }
}
