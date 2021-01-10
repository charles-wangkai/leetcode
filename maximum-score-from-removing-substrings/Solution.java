class Solution {
  public int maximumGain(String s, int x, int y) {
    return (x >= y)
        ? computeScore(s, new String[] {"ab", "ba"}, new int[] {x, y})
        : computeScore(s, new String[] {"ba", "ab"}, new int[] {y, x});
  }

  int computeScore(String s, String[] substrings, int[] scores) {
    int result = 0;
    for (int i = 0; i < substrings.length; ++i) {
      StringBuilder sb = new StringBuilder();
      for (char ch : s.toCharArray()) {
        if (ch == substrings[i].charAt(1)
            && sb.length() != 0
            && sb.charAt(sb.length() - 1) == substrings[i].charAt(0)) {
          sb.deleteCharAt(sb.length() - 1);
          result += scores[i];
        } else {
          sb.append(ch);
        }
      }

      s = sb.toString();
    }

    return result;
  }
}
