import java.util.Arrays;

class Solution {
  public int maxFrequency(int[] nums, int k) {
    nums = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

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
    for (int i = 0; i < nums.length; ++i) {
      if (i >= frequency) {
        k += nums[i - 1] - nums[i - frequency];
      }
      if (i != 0) {
        k -= (nums[i] - nums[i - 1]) * Math.min(i, frequency - 1);
      }

      if (i >= frequency - 1 && k >= 0) {
        return true;
      }
    }

    return false;
  }
}
