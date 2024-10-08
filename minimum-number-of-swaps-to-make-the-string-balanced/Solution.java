class Solution {
  public int minSwaps(String s) {
    int minDepth = 0;
    int depth = 0;
    for (char c : s.toCharArray()) {
      if (c == '[') {
        ++depth;
      } else {
        --depth;
        minDepth = Math.min(minDepth, depth);
      }
    }

    return Math.max(0, (-minDepth + 1) / 2);
  }
}
