class Solution {
  public int maxScore(String s) {
    int result = 0;
    int oneCount = (int) s.chars().filter(c -> c == '1').count();
    int zeroCount = 0;
    for (int i = 0; i < s.length() - 1; ++i) {
      if (s.charAt(i) == '0') {
        ++zeroCount;
      } else {
        --oneCount;
      }

      result = Math.max(result, zeroCount + oneCount);
    }

    return result;
  }
}
