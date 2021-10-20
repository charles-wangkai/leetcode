class Solution {
  public int countMaxOrSubsets(int[] nums) {
    int maxOr = 0;
    int result = 0;
    for (int code = 1; code < 1 << nums.length; ++code) {
      int or = 0;
      for (int i = 0; i < nums.length; ++i) {
        if ((code & (1 << i)) != 0) {
          or |= nums[i];
        }
      }

      if (or > maxOr) {
        maxOr = or;
        result = 1;
      } else if (or == maxOr) {
        ++result;
      }
    }

    return result;
  }
}
