class Solution {
  public String countAndSay(int n) {
    String curr = "1";
    for (int i = 0; i < n - 1; ++i) {
      StringBuilder next = new StringBuilder();
      char digit = curr.charAt(0);
      int count = 1;
      for (int j = 1; j <= curr.length(); ++j) {
        if (j != curr.length() && curr.charAt(j) == digit) {
          ++count;
        } else {
          next.append(count).append(digit);
          if (j != curr.length()) {
            digit = curr.charAt(j);
            count = 1;
          }
        }
      }

      curr = next.toString();
    }

    return curr;
  }
}
