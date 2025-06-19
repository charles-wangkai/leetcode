import java.util.Arrays;

class Solution {
  public int partitionArray(int[] nums, int k) {
    Arrays.sort(nums);

    int result = 0;
    int limit = -1;
    for (int x : nums) {
      if (x > limit) {
        ++result;
        limit = x + k;
      }
    }

    return result;
  }
}