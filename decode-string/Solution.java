class Solution {
  public String decodeString(String s) {
    StringBuilder result = new StringBuilder();
    int level = 0;
    int startIndex = -1;
    int k = 0;
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      if (ch == '[') {
        if (level == 0) {
          startIndex = i + 1;
        }

        ++level;
      } else if (ch == ']') {
        --level;

        if (level == 0) {
          result.append(decodeString(s.substring(startIndex, i)).repeat(k));
          k = 0;
        }
      } else if (level == 0) {
        if (Character.isDigit(ch)) {
          k = k * 10 + (ch - '0');
        } else {
          result.append(ch);
        }
      }
    }

    return result.toString();
  }
}
