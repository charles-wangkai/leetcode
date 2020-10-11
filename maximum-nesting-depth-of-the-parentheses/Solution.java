class Solution {
  public int maxDepth(String s) {
    int result = 0;
    int depth = 0;
    for (char ch : s.toCharArray()) {
      if (ch == '(') {
        ++depth;
        result = Math.max(result, depth);
      } else if (ch == ')') {
        --depth;
      }
    }

    return result;
  }
}
