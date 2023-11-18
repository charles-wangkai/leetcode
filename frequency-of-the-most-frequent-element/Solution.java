import java.util.Arrays;

class Solution {
  public int maxFrequency(int[] nums, int k) {
    Arrays.sort(nums);

    int result = -1;
    int lower = 1;
    int upper = nums.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int k, int frequency) {
    long operationNum = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (i >= frequency) {
        operationNum -= nums[i - 1] - nums[i - frequency];
      }
      if (i != 0) {
        operationNum += (nums[i] - nums[i - 1]) * Math.min(i, frequency - 1L);
      }

      if (i >= frequency - 1 && operationNum <= k) {
        return true;
      }
    }

    return false;
  }
}
