class Solution {
  public int search(int[] nums, int target) {
    int lower = 0;
    int upper = nums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (nums[middle] == target) {
        return middle;
      }

      if (nums[lower] <= nums[middle]) {
        if (target >= nums[lower] && target < nums[middle]) {
          upper = middle - 1;
        } else {
          lower = middle + 1;
        }
      } else {
        if (target > nums[middle] && target <= nums[upper]) {
          lower = middle + 1;
        } else {
          upper = middle - 1;
        }
      }
    }

    return -1;
  }
}
