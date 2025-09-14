// https://github.com/charles-wangkai/codeforces/blob/master/acmsguru/275/Main.java

import java.util.Arrays;
import java.util.OptionalInt;

class Solution {
  static final int BIT_NUM = 30;

  public int maxXorSubsequences(int[] nums) {
    int result = 0;
    for (int b = BIT_NUM - 1; b >= 0; --b) {
      int b_ = b;
      OptionalInt chosen = Arrays.stream(nums).filter(x -> ((x >> b_) & 1) == 1).findAny();
      if (chosen.isPresent()) {
        if (((result >> b) & 1) == 0) {
          result ^= chosen.getAsInt();
        }

        for (int i = 0; i < nums.length; ++i) {
          if (((nums[i] >> b) & 1) == 1) {
            nums[i] ^= chosen.getAsInt();
          }
        }
      }
    }

    return result;
  }
}