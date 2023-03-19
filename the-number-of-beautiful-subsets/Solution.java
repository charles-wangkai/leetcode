import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 1000;

  public int beautifulSubsets(int[] nums, int k) {
    return (int) IntStream.range(1, 1 << nums.length).filter(mask -> check(nums, k, mask)).count();
  }

  boolean check(int[] nums, int k, int mask) {
    boolean[] invalids = new boolean[LIMIT + 1];
    for (int i = 0; i < nums.length; ++i) {
      if (((mask >> i) & 1) == 1) {
        if (invalids[nums[i]]) {
          return false;
        }

        if (nums[i] >= k) {
          invalids[nums[i] - k] = true;
        }
        if (nums[i] + k < invalids.length) {
          invalids[nums[i] + k] = true;
        }
      }
    }

    return true;
  }
}
