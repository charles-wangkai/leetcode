class Solution {
  public int countBinarySubstrings(String s) {
    int result = 0;
    int prevCount = 0;
    int currCount = 0;
    char curr = '0';
    for (char c : s.toCharArray()) {
      if (c != curr) {
        prevCount = currCount;
        currCount = 0;
        curr = (char) ('0' + '1' - curr);
      }

      ++currCount;
      if (currCount <= prevCount) {
        ++result;
      }
    }

    return result;
  }
}
