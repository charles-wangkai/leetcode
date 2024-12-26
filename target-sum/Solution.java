class Solution {
  public int findTargetSumWays(int[] nums, int target) {
    return search(nums, target, 0);
  }

  int search(int[] nums, int rest, int index) {
    if (index == nums.length) {
      return (rest == 0) ? 1 : 0;
    }

    return search(nums, rest + nums[index], index + 1)
        + search(nums, rest - nums[index], index + 1);
  }
}
