import java.util.Arrays;

class Solution {
  public int[] applyOperations(int[] nums) {
    for (int i = 0; i < nums.length - 1; ++i) {
      if (nums[i] == nums[i + 1]) {
        nums[i] *= 2;
        nums[i + 1] = 0;
      }
    }

    return Arrays.copyOf(Arrays.stream(nums).filter(x -> x != 0).toArray(), nums.length);
  }
}
