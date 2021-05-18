class Solution {
  public int subsetXORSum(int[] nums) {
    int result = 0;
    for (int code = 0; code < 1 << nums.length; ++code) {
      int xor = 0;
      for (int i = 0; i < nums.length; ++i) {
        if ((code & (1 << i)) != 0) {
          xor ^= nums[i];
        }
      }

      result += xor;
    }

    return result;
  }
}
