class Solution {
  public int maxSum(int[] nums) {
    int result = -1;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        if (String.valueOf(nums[i]).chars().max().getAsInt()
            == String.valueOf(nums[j]).chars().max().getAsInt()) {
          result = Math.max(result, nums[i] + nums[j]);
        }
      }
    }

    return result;
  }
}
