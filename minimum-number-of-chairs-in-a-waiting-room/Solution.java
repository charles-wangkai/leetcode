class Solution {
  public int minimumChairs(String s) {
    int result = 0;
    int depth = 0;
    for (char c : s.toCharArray()) {
      if (c == 'E') {
        ++depth;
        result = Math.max(result, depth);
      } else {
        --depth;
      }
    }

    return result;
  }
}