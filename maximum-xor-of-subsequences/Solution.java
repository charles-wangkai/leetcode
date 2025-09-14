// https://github.com/charles-wangkai/codeforces/blob/master/acmsguru/275/Main.java

class Solution {
  static final int BIT_NUM = 30;

  public int maxXorSubsequences(int[] nums) {
    int result = 0;
    for (int b = BIT_NUM - 1; b >= 0; --b) {
      for (int i = 0; i < nums.length; ++i) {
        if (((nums[i] >> b) & 1) == 1) {
          if (((result >> b) & 1) == 0) {
            result ^= nums[i];
          }

          int temp = nums[i];
          for (int j = 0; j < nums.length; ++j) {
            if (((nums[j] >> b) & 1) == 1) {
              nums[j] ^= temp;
            }
          }

          break;
        }
      }
    }

    return result;
  }
}