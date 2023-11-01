import java.util.Arrays;

class Solution {
  static final int BIT_NUM = 31;

  public int findKOr(int[] nums, int k) {
    int result = 0;
    for (int i = 0; i < BIT_NUM; ++i) {
      int i_ = i;
      if (Arrays.stream(nums).filter(num -> ((num >> i_) & 1) == 1).count() >= k) {
        result |= 1 << i;
      }
    }

    return result;
  }
}
