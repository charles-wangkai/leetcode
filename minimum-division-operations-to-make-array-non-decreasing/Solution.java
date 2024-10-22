import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums) {
    int[] divided = new int[Arrays.stream(nums).max().getAsInt() + 1];
    Arrays.fill(divided, -1);
    for (int i = 2; i < divided.length; ++i) {
      for (int j = i * 2; j < divided.length; j += i) {
        if (divided[j] == -1) {
          divided[j] = i;
        }
      }
    }

    int result = 0;
    for (int i = nums.length - 2; i >= 0; --i) {
      if (nums[i] > nums[i + 1]) {
        if (divided[nums[i]] == -1 || divided[nums[i]] > nums[i + 1]) {
          return -1;
        }

        nums[i] = divided[nums[i]];
        ++result;
      }
    }

    return result;
  }
}