import java.util.stream.IntStream;

class Solution {
  public boolean canSortArray(int[] nums) {
    while (true) {
      boolean changed = false;
      for (int i = 0; i < nums.length - 1; ++i) {
        if (nums[i] > nums[i + 1] && Integer.bitCount(nums[i]) == Integer.bitCount(nums[i + 1])) {
          int temp = nums[i];
          nums[i] = nums[i + 1];
          nums[i + 1] = temp;

          changed = true;
        }
      }

      if (!changed) {
        break;
      }
    }

    return IntStream.range(0, nums.length - 1).allMatch(i -> nums[i] <= nums[i + 1]);
  }
}