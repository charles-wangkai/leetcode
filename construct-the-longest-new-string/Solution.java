class Solution {
  public int longestString(int x, int y, int z) {
    return 2 * (Math.min(x, Math.min(x, y) + 1) + Math.min(y, Math.min(x, y) + 1) + z);
  }
}
