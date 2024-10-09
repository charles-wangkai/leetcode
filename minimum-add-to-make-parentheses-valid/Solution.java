class Solution {
  public int minAddToMakeValid(String s) {
    int result = 0;
    int depth = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        ++depth;
      } else if (depth == 0) {
        ++result;
      } else {
        --depth;
      }
    }
    result += depth;

    return result;
  }
}
