class Solution {
  public int longestPalindromicSubsequence(String s, int k) {
    return search(new int[s.length()][s.length()][k + 1], s, 0, s.length() - 1, k);
  }

  int search(int[][][] cache, String s, int beginIndex, int endIndex, int rest) {
    if (beginIndex > endIndex) {
      return 0;
    }
    if (beginIndex == endIndex) {
      return 1;
    }

    if (cache[beginIndex][endIndex][rest] == 0) {
      int result =
          Math.max(
              search(cache, s, beginIndex + 1, endIndex, rest),
              search(cache, s, beginIndex, endIndex - 1, rest));

      int diff = Math.floorMod(s.charAt(beginIndex) - s.charAt(endIndex), 26);
      int operationNum = Math.min(diff, 26 - diff);
      if (operationNum <= rest) {
        result =
            Math.max(
                result, 2 + search(cache, s, beginIndex + 1, endIndex - 1, rest - operationNum));
      }

      cache[beginIndex][endIndex][rest] = result;
    }

    return cache[beginIndex][endIndex][rest];
  }
}
