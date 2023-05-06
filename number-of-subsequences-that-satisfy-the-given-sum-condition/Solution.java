import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numSubseq(int[] nums, int target) {
    int[] twoPowers = new int[nums.length + 1];
    twoPowers[0] = 1;
    for (int i = 1; i < nums.length; ++i) {
      twoPowers[i] = multiplyMod(twoPowers[i - 1], 2);
    }

    Arrays.sort(nums);

    int result = 0;
    int endIndex = nums.length - 1;
    for (int beginIndex = 0; ; ++beginIndex) {
      while (endIndex >= beginIndex && nums[beginIndex] + nums[endIndex] > target) {
        --endIndex;
      }
      if (endIndex < beginIndex) {
        break;
      }

      result = addMod(result, twoPowers[endIndex - beginIndex]);
    }

    return result;
  }

  static int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  static int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
