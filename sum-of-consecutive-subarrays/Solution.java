import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int getSum(int[] nums) {
    return addMod(
        Arrays.stream(nums).reduce(this::addMod).getAsInt(),
        addMod(computeSum(nums, 1), computeSum(nums, -1)));
  }

  int computeSum(int[] nums, int diff) {
    int result = 0;
    int beginIndex = 0;
    while (beginIndex != nums.length) {
      int endIndex = beginIndex;
      while (endIndex != nums.length - 1 && nums[endIndex + 1] - nums[endIndex] == diff) {
        ++endIndex;
      }

      for (int i = beginIndex; i <= endIndex; ++i) {
        result =
            addMod(
                result,
                multiplyMod(
                    nums[i], addMod(multiplyMod(i - beginIndex + 1, endIndex - i + 1), -1)));
      }

      beginIndex = endIndex + 1;
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
