class Solution {
  public int maximumStrongPairXor(int[] nums) {
    int result = -1;
    for (int x : nums) {
      for (int y : nums) {
        if (Math.abs(x - y) <= Math.min(x, y)) {
          result = Math.max(result, x ^ y);
        }
      }
    }

    return result;
  }
}
