class Solution {
  public int findMin(int[] nums) {
    int lower = 0;
    int upper = nums.length - 1;
    while (nums[lower] > nums[upper]) {
      int middle = (lower + upper) / 2;
      if (nums[middle] >= nums[lower]) {
        lower = middle + 1;
      } else {
        upper = middle;
      }
    }

    return nums[lower];
  }
}
