import java.util.stream.IntStream;

class Solution {
  public boolean checkEqualPartitions(int[] nums, long target) {
    return IntStream.range(1, 1 << nums.length)
        .anyMatch(
            mask -> isSameProduct(nums, target, mask, 0) && isSameProduct(nums, target, mask, 1));
  }

  boolean isSameProduct(int[] nums, long target, int mask, int bit) {
    long product = 1;
    for (int i = 0; i < nums.length; ++i) {
      if (((mask >> i) & 1) == bit) {
        product *= nums[i];
        if (product > target) {
          return false;
        }
      }
    }

    return product == target;
  }
}