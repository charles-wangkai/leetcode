class Solution {
  public int maximumLengthSubstring(String s) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      int[] counts = new int[26];
      for (int endIndex = beginIndex; endIndex < s.length(); ++endIndex) {
        ++counts[s.charAt(endIndex) - 'a'];
        if (counts[s.charAt(endIndex) - 'a'] == 3) {
          break;
        }

        result = Math.max(result, endIndex - beginIndex + 1);
      }
    }

    return result;
  }
}