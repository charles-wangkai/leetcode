class Solution {
  public boolean search(int[] nums, int target) {
    return search(nums, target, 0, nums.length - 1);
  }

  boolean search(int[] nums, int target, int lower, int upper) {
    if (lower > upper
        || (nums[lower] < nums[upper] && !(target >= nums[lower] && target <= nums[upper]))) {
      return false;
    }

    int middle = (lower + upper) / 2;
    if (nums[middle] == target) {
      return true;
    }

    return search(nums, target, lower, middle - 1) || search(nums, target, middle + 1, upper);
  }
}
