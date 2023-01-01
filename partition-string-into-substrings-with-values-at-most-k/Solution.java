class Solution {
  public int minimumPartition(String s, int k) {
    int result = 0;
    int beginIndex = 0;
    while (beginIndex != s.length()) {
      int endIndex = beginIndex - 1;
      while (endIndex + 1 != s.length()
          && Long.parseLong(s.substring(beginIndex, endIndex + 2)) <= k) {
        ++endIndex;
      }
      if (endIndex == beginIndex - 1) {
        return -1;
      }

      ++result;
      beginIndex = endIndex + 1;
    }

    return result;
  }
}
