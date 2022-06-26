class Solution {
  public int countAsterisks(String s) {
    int result = 0;
    int barCount = 0;
    for (char c : s.toCharArray()) {
      if (c == '|') {
        ++barCount;
      } else if (c == '*' && barCount % 2 == 0) {
        ++result;
      }
    }

    return result;
  }
}