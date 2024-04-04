class Solution {
  public int maxDepth(String s) {
    int result = 0;
    int depth = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        ++depth;
        result = Math.max(result, depth);
      } else if (c == ')') {
        --depth;
      }
    }

    return result;
  }
}
