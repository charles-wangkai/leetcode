class Solution {
  public int countBinarySubstrings(String s) {
    int result = 0;
    int prevCount = 0;
    int currCount = 0;
    char target = '0';
    for (char current : s.toCharArray()) {
      if (current != target) {
        prevCount = currCount;
        currCount = 0;
        target = (char) ('0' + '1' - target);
      }

      ++currCount;
      if (currCount <= prevCount) {
        ++result;
      }
    }

    return result;
  }
}
