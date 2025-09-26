import java.util.Arrays;

class Solution {
  public int triangleNumber(int[] nums) {
    Arrays.sort(nums);

    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        result += computeLongestSideNum(nums, nums[i] + nums[j], j);
      }
    }

    return result;
  }

  int computeLongestSideNum(int[] nums, int limit, int fromIndex) {
    int result = 0;
    int lower = fromIndex + 1;
    int upper = nums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (nums[middle] < limit) {
        result = middle - fromIndex;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
